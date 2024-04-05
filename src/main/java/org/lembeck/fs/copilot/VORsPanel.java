package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.FacilityDataType;
import org.lembeck.fs.simconnect.constants.VorType;
import org.lembeck.fs.simconnect.request.FacilityListType;
import org.lembeck.fs.simconnect.request.RequestFacilityDataEx1Request;
import org.lembeck.fs.simconnect.response.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.*;

public class VORsPanel extends JPanel {

    private final SimConnect simConnect;

    private final SimViewer simViewer;

    private final int LOAD_VORS_REQUEST_ID = SimConnect.getNextUserRequestID();

    private final int VOR_DEFINE_ID = SimConnect.getNextUserDefineID();

    private final JTable vorsTable;

    private final VORsTableModel vorsTableModel = new VORsTableModel();

    private final HashMap<IcaoRegion, VorFacilityData> vorDataCache = new HashMap<>();

    final List<VorInfo> vorBuffer = new ArrayList<>();

    public VORsPanel(SimConnect simConnect, SimViewer simViewer) {
        this.simConnect = simConnect;
        this.simViewer = simViewer;
        setLayout(new BorderLayout());
        JButton loadVORsButton = new JButton(new LoadVORsAction());
        add(loadVORsButton, BorderLayout.SOUTH);
        simConnect.getRequestReceiver().addVorListHandler(this::handleVorsList);
        vorsTable = new JTable(vorsTableModel);
        vorsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sp = new JScrollPane(vorsTable);
        add(sp, BorderLayout.CENTER);

        try {
            initFacilityDefinitions();
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
        vorsTableModel.addTableModelListener(this::tableModelChanged);
    }

    private void tableModelChanged(TableModelEvent tableModelEvent) {
/*        for (int row = tableModelEvent.getFirstRow(); row <= Math.min(vorsTableModel.getRowCount() - 1, tableModelEvent.getLastRow()); row++) {
            Object value = vorsTableModel.getValueAt(row, 6);
            TableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            Dimension preferredSize = cellRenderer.getTableCellRendererComponent(vorsTable, value, false, false, row, 6).getPreferredSize();
            vorsTable.setRowHeight(row, preferredSize.height);
            int minWidth = vorsTable.getColumnModel().getColumn(6).getMinWidth();
            if (preferredSize.width > minWidth) {
                vorsTable.getColumnModel().getColumn(6).setMinWidth(preferredSize.width);
            }
        }
        */
    }

    private void handleVorsList(RecvVorListResponse recvVorListResponse) {
        if (recvVorListResponse.getRequestID() == LOAD_VORS_REQUEST_ID) {
            if (recvVorListResponse.getEntryNumber() == 0) {
                vorBuffer.clear();
            }
            final LatLonAlt position = simViewer.getLastPosition();
            Arrays.stream(recvVorListResponse.getVorList()).map(vor -> convertVor(vor, position)).forEach(vorBuffer::add);

            if (recvVorListResponse.getEntryNumber() == recvVorListResponse.getOutOf() - 1) {
                vorBuffer.sort(Comparator.comparing(VorInfo::getDistanceNM));

                ((VORsTableModel) vorsTable.getModel()).replaceValues(vorBuffer);
                vorBuffer.stream().forEach(vorInfo -> this.loadVorDetails(vorInfo.getIcao(), vorInfo.getRegionCode()));
            }
        }
    }

    private final HashMap<Integer, IcaoRegion> requestMap = new HashMap<>();

    private void loadVorDetails(String icao, String regionCode) {
        VorFacilityData vorFacilityData = vorDataCache.get(new IcaoRegion(icao, regionCode));
        if (vorFacilityData == null) {
            try {
                final int requestID = SimConnect.getNextUserRequestID();
                requestMap.put(requestID, new IcaoRegion(icao, regionCode));
                simConnect.requestFacilityDataEx1(VOR_DEFINE_ID, requestID, icao, regionCode, RequestFacilityDataEx1Request.FacilityType.VOR);
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        } else {
            vorsTableModel.addDetails(vorFacilityData);
        }
    }

    private VorInfo convertVor(FacilityVOR vor, LatLonAlt position) {
        double distanceNM = position == null ? 0 : SimUtil.meterToNM(SimUtil.distance(position.getLatitude(), position.getLongitude(), vor.getLatitude(), vor.getLongitude()));
        double heading = position == null ? 0 : SimUtil.heading(position.getLatitude(), position.getLongitude(), vor.getLatitude(), vor.getLongitude());
        return new VorInfo(vor.getIcao(), vor.getRegionCode(), vor.getLongitude(), vor.getLatitude(), vor.getAltitude(), distanceNM, heading);
    }

    class LoadVORsAction extends AbstractAction {

        LoadVORsAction() {
            super();
            putValue(NAME, "load VORs");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                simConnect.requestFacilitiesListEx1(FacilityListType.VOR, LOAD_VORS_REQUEST_ID);
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }
    }

    private void initFacilityDefinitions() throws IOException {
        simConnect.getRequestReceiver().addFacilityDataHandler(this::handleFacilityData);
        simConnect.getRequestReceiver().addFacilityDataEndHandler(this::handleFacilityDataEnd);

        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "OPEN VOR");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "NAME");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "IS_NAV");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "IS_DME");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "IS_TACAN");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "FREQUENCY");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "TYPE");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "NAV_RANGE");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "MAGVAR");
        simConnect.addToFacilityDefinition(VOR_DEFINE_ID, "CLOSE VOR");
    }

    private VorFacilityData currentVorData;

    private void handleFacilityData(RecvFacilityDataResponse facilityDataResponse) {
        if (requestMap.containsKey(facilityDataResponse.getRequestID()) && facilityDataResponse.getType() == FacilityDataType.VOR) {
            IcaoRegion icaoRegion = requestMap.get(facilityDataResponse.getRequestID());
            ByteBuffer buffer = facilityDataResponse.getDataBuffer();
            String name = SimUtil.readString(buffer, 64);
            boolean nav = buffer.getInt() != 0;
            boolean dme = buffer.getInt() != 0;
            boolean tacan = buffer.getInt() != 0;
            int frequency = buffer.getInt();
            VorType type = VorType.ofId(buffer.getInt());
            float navRangeMeters = buffer.getFloat();
            float magVar = buffer.getFloat();
            currentVorData = new VorFacilityData(icaoRegion.icao, icaoRegion.reagion, name, nav, dme, tacan, frequency, type, navRangeMeters, magVar);
        }
    }

    private void handleFacilityDataEnd(RecvFacilityDataEndResponse recvFacilityDataEndResponse) {
        if (requestMap.containsKey(recvFacilityDataEndResponse.getRequestID())) {
            vorDataCache.put(new IcaoRegion(currentVorData.getIcao(), currentVorData.getRegion()), currentVorData);
            vorsTableModel.addDetails(currentVorData);
            currentVorData = null;
        }
    }

    static class VorFacilityData {

        private final String name;
        private final boolean nav;
        private final boolean dme;
        private final boolean tacan;
        private final int frequency;
        private final VorType type;
        private final float navRangeMeters;
        private final float magVar;
        private final String icao;
        private final String region;

        public VorFacilityData(String icao, String region, String name, boolean nav, boolean dme, boolean tacan, int frequency, VorType type, float navRangeMeters, float magVar) {
            this.icao = icao;
            this.region = region;
            this.name = name;
            this.nav = nav;
            this.dme = dme;
            this.tacan = tacan;
            this.frequency = frequency;
            this.type = type;
            this.navRangeMeters = navRangeMeters;
            this.magVar = magVar;
        }

        public String getName() {
            return name;
        }

        public boolean isNav() {
            return nav;
        }

        public boolean isDme() {
            return dme;
        }

        public boolean isTacan() {
            return tacan;
        }

        public int getFrequency() {
            return frequency;
        }

        public VorType getType() {
            return type;
        }

        public float getNavRangeMeters() {
            return navRangeMeters;
        }

        public float getMagVar() {
            return magVar;
        }

        public String getIcao() {
            return icao;
        }

        public String getRegion() {
            return region;
        }
    }

    record IcaoRegion(String icao, String reagion) {
    }
}