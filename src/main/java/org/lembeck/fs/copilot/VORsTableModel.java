package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.SimUtil;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.event.TableModelEvent.ALL_COLUMNS;

public class VORsTableModel implements TableModel {

    public static final NumberFormat nf0 = DecimalFormat.getNumberInstance();
    public static final NumberFormat nf1 = DecimalFormat.getNumberInstance();
    public static final NumberFormat nf3 = DecimalFormat.getNumberInstance();

    static {
        nf0.setMinimumFractionDigits(0);
        nf0.setMaximumFractionDigits(0);
        nf1.setMinimumFractionDigits(1);
        nf1.setMaximumFractionDigits(1);
        nf3.setMinimumFractionDigits(3);
        nf3.setMaximumFractionDigits(3);
    }

    private final List<VorInfo> vors = new ArrayList<>();

    private final List<TableModelListener> listeners = new ArrayList<>();

    @Override
    public int getRowCount() {
        return vors.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ICAO";
            case 1 -> "Dst (NM)";
            case 2 -> "Hdg";
            case 3 -> "Range (NM)";
            case 4 -> "Frequency";
            case 5 -> "Type";
            case 6 -> "NAV";
            case 7 -> "DME";
            case 8 -> "TACAN";
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> String.class;
            case 2 -> String.class;
            case 3 -> String.class;
            case 4 -> String.class;
            case 5 -> String.class;
            case 6 -> Boolean.class;
            case 7 -> Boolean.class;
            case 8 -> Boolean.class;
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VorInfo vor = vors.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> vor.getIcao() + (vor.getName() == null ? "" : (" " + vor.getName()));
            case 1 -> nf1.format(vor.getDistanceNM());
            case 2 -> nf0.format(vor.getHeading());
            case 3 -> nf0.format(SimUtil.meterToNM(vor.getRangeMeters()));
            case 4 -> vor.getFrequencyString();
            case 5 -> vor.getType();
            case 6 -> vor.isNav();
            case 7 -> vor.isDme();
            case 8 -> vor.isTacan();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    void replaceValues(List<VorInfo> newVors) {
        this.vors.clear();
        this.vors.addAll(newVors);
        TableModelEvent event = new TableModelEvent(this);
        listeners.forEach(l -> l.tableChanged(event));
    }

    public void addDetails(VORsPanel.VorFacilityData vorFacilityData) {
        String icao = vorFacilityData.getIcao();
        String region = vorFacilityData.getRegion();
        List<VorInfo> vorInfos = this.vors;
        for (int i = 0; i < vorInfos.size(); i++) {
            VorInfo vor = vorInfos.get(i);
            if (vor.getIcao().equalsIgnoreCase(icao) && vor.getRegionCode().equalsIgnoreCase(region)) {
                vor.setName(vorFacilityData.getName());
                vor.setFrequency(vorFacilityData.getFrequency());
                vor.setRangeMeters(vorFacilityData.getNavRangeMeters());
                vor.setDme(vorFacilityData.isDme());
                vor.setNav(vorFacilityData.isNav());
                vor.setTacan(vorFacilityData.isTacan());
                vor.setMagVar(vorFacilityData.getMagVar());
                vor.setType(vorFacilityData.getType());
                TableModelEvent event = new TableModelEvent(this, i, i, ALL_COLUMNS, TableModelEvent.UPDATE);
                listeners.forEach(listeners -> listeners.tableChanged(event));
                break;
            }
        }
    }
}