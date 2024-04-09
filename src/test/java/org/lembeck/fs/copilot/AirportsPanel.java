package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.FacilityDataType;
import org.lembeck.fs.simconnect.constants.RunwayDesignator;
import org.lembeck.fs.simconnect.constants.RunwaySurface;
import org.lembeck.fs.simconnect.request.FacilityListType;
import org.lembeck.fs.simconnect.response.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.*;

public class AirportsPanel extends JPanel {

    private final SimConnect simConnect;

    private final SimViewer simViewer;

    private final int LOAD_AIRPORTS_REQUEST_ID = SimConnect.getNextUserRequestID();

    private final int LOAD_AIRPORT_DATA_REQUEST_ID = SimConnect.getNextUserRequestID();

    private final int AIRPORT_DEFINE_ID = SimConnect.getNextUserDefineID();

    private final JTable airportsTable;

    private final AirportsTableModel airportsTableModel = new AirportsTableModel();

    private final HashMap<String, AirportFacilityData> airportDataCache = new HashMap<>();

    final java.util.List<AirportInfo> airportBuffer = new ArrayList<>();

    public AirportsPanel(SimConnect simConnect, SimViewer simViewer) {
        this.simConnect = simConnect;
        this.simViewer = simViewer;
        setLayout(new BorderLayout());
        JButton loadAirportsButton = new JButton(new LoadAirportsAction());
        add(loadAirportsButton, BorderLayout.SOUTH);
        simConnect.getRequestReceiver().addAirportListHandler(this::handleAirportsList);
        airportsTable = new JTable(airportsTableModel);
        airportsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sp = new JScrollPane(airportsTable);
        add(sp, BorderLayout.CENTER);

        try {
            initFacilityDefinitions();
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
        airportsTableModel.addTableModelListener(this::tableModelChanged);
    }

    private void tableModelChanged(TableModelEvent tableModelEvent) {
        for (int row = tableModelEvent.getFirstRow(); row <= Math.min(airportsTableModel.getRowCount() - 1, tableModelEvent.getLastRow()); row++) {
            Object value = airportsTableModel.getValueAt(row, 6);
            TableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            Dimension preferredSize = cellRenderer.getTableCellRendererComponent(airportsTable, value, false, false, row, 6).getPreferredSize();
            airportsTable.setRowHeight(row, preferredSize.height);
            int minWidth = airportsTable.getColumnModel().getColumn(6).getMinWidth();
            if (preferredSize.width > minWidth) {
                airportsTable.getColumnModel().getColumn(6).setMinWidth(preferredSize.width);
            }
        }
    }

    private void handleAirportsList(RecvAirportListResponse recvAirportListResponse) {
        if (recvAirportListResponse.getRequestID() == LOAD_AIRPORTS_REQUEST_ID) {
            if (recvAirportListResponse.getEntryNumber() == 0) {
                airportBuffer.clear();
            }
            final LatLonAlt position = simViewer.getLastPosition();
            Arrays.stream(recvAirportListResponse.getAirportList()).map(airport -> convertAirport(airport, position)).forEach(airportBuffer::add);

            if (recvAirportListResponse.getEntryNumber() == recvAirportListResponse.getOutOf() - 1) {
                airportBuffer.sort(Comparator.comparing(AirportInfo::getDistance));

                ((AirportsTableModel) airportsTable.getModel()).replaceValues(airportBuffer);
                airportBuffer.stream().map(AirportInfo::getIcao).forEach(this::loadAirportDetails);
            }
        }
    }

    private void loadAirportDetails(String icao) {
        AirportFacilityData airportFacilityData = airportDataCache.get(icao);
        if (airportFacilityData == null) {
            try {
                simConnect.requestFacilityDataEx1(AIRPORT_DEFINE_ID, LOAD_AIRPORT_DATA_REQUEST_ID, icao, null, null);
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        } else {
            airportsTableModel.addDetails(airportFacilityData);
        }
    }

    private AirportInfo convertAirport(FacilityAirport airport, LatLonAlt position) {
        double distance = position == null ? 0 : SimUtil.meterToNM(SimUtil.distance(position.getLatitude(), position.getLongitude(), airport.getLatitude(), airport.getLongitude()));
        double heading = position == null ? 0 : SimUtil.heading(position.getLatitude(), position.getLongitude(), airport.getLatitude(), airport.getLongitude());
        return new AirportInfo(airport.getIcao(), airport.getLongitude(), airport.getLatitude(), airport.getAltitude(), distance, heading);
    }

    class LoadAirportsAction extends AbstractAction {

        LoadAirportsAction() {
            super();
            putValue(NAME, "load airports");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                simConnect.requestFacilitiesListEx1(FacilityListType.AIRPORT, LOAD_AIRPORTS_REQUEST_ID);
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }
    }

    private void initFacilityDefinitions() throws IOException {
        simConnect.getRequestReceiver().addFacilityDataHandler(this::handleFacilityData);
        simConnect.getRequestReceiver().addFacilityDataEndHandler(this::handleFacilityDataEnd);

        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "OPEN AIRPORT");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "ICAO");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "NAME64");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "N_RUNWAYS");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "OPEN RUNWAY");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "PRIMARY_ILS_ICAO");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "PRIMARY_NUMBER");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "PRIMARY_DESIGNATOR");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "SECONDARY_NUMBER");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "SECONDARY_DESIGNATOR");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "HEADING");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "LENGTH");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "SURFACE");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "CLOSE RUNWAY");
        simConnect.addToFacilityDefinition(AIRPORT_DEFINE_ID, "CLOSE AIRPORT");

    }

    private AirportFacilityData currentAirportData;

    private void handleFacilityData(RecvFacilityDataResponse facilityDataResponse) {
        if (facilityDataResponse.getRequestID() == LOAD_AIRPORT_DATA_REQUEST_ID && facilityDataResponse.getType() == FacilityDataType.AIRPORT) {
            ByteBuffer buffer = facilityDataResponse.getDataBuffer();
            String icao = SimUtil.readString(buffer, 8);
            String name = SimUtil.readString(buffer, 64);
            int nRunways = buffer.getInt();
            currentAirportData = new AirportFacilityData(icao, name);
        }
        if (facilityDataResponse.getRequestID() == LOAD_AIRPORT_DATA_REQUEST_ID && facilityDataResponse.getType() == FacilityDataType.RUNWAY) {
            ByteBuffer buffer = facilityDataResponse.getDataBuffer();
            String icao = SimUtil.readString(buffer, 8);
            int primaryNumber = buffer.getInt();
            RunwayDesignator primaryDesignator = RunwayDesignator.ofId(buffer.getInt());
            int secondaryNumber = buffer.getInt();
            RunwayDesignator secondaryDesignator = RunwayDesignator.ofId(buffer.getInt());
            float heading = buffer.getFloat();
            float length = buffer.getFloat();
            RunwaySurface surface = RunwaySurface.ofId(buffer.getInt());
            RunwayFacilityData runway = new RunwayFacilityData(icao, primaryNumber, primaryDesignator, secondaryNumber, secondaryDesignator, heading, length, surface);
            currentAirportData.addRunway(runway);
        }
    }

    private void handleFacilityDataEnd(RecvFacilityDataEndResponse recvFacilityDataEndResponse) {
        if (recvFacilityDataEndResponse.getRequestID() == LOAD_AIRPORT_DATA_REQUEST_ID) {
            airportDataCache.put(currentAirportData.getIcao(), currentAirportData);
            airportsTableModel.addDetails(currentAirportData);
            currentAirportData = null;
        }
    }

    static class AirportFacilityData {

        private final String icao;

        private final String name;

        private final java.util.List<RunwayFacilityData> runways = new ArrayList<>(2);

        public AirportFacilityData(String icao, String name) {
            this.icao = icao;
            this.name = name;
        }

        public void addRunway(RunwayFacilityData runway) {
            this.runways.add(runway);
        }

        public String getIcao() {
            return icao;
        }

        public String getName() {
            return name;
        }

        public List<RunwayFacilityData> getRunways() {
            return runways;
        }
    }

    record RunwayFacilityData(String icao, int primaryNumber, RunwayDesignator primaryDesignator, int secondaryNumber,
                              RunwayDesignator secondaryDesignator, float heading, float length,
                              RunwaySurface surface) {

        @Override
        public String toString() {
            return primaryNumber + primaryDesignator.getShortName() + "/" +
                    secondaryNumber + secondaryDesignator.getShortName() + " " +
                    (int) length + "m" + " " + surface +
                    (icao == null || icao.isBlank() ? "" : ("(" + icao + ")"));
        }
    }
}