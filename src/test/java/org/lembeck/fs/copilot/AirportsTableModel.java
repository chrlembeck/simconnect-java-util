package org.lembeck.fs.copilot;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.event.TableModelEvent.ALL_COLUMNS;

public class AirportsTableModel implements TableModel {

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

    private final List<AirportInfo> airports = new ArrayList<>();

    private final List<TableModelListener> listeners = new ArrayList<>();

    @Override
    public int getRowCount() {
        return airports.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ICAO";
            case 1 -> "Lat";
            case 2 -> "Lon";
            case 3 -> "Alt";
            case 4 -> "Dst";
            case 5 -> "Hdg";
            case 6 -> "Runways";
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
            case 6 -> String.class;
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AirportInfo airport = airports.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> airport.getIcao() + (airport.getName() == null ? "" : (" " + airport.getName()));
            case 1 -> nf3.format(airport.getLatitude());
            case 2 -> nf3.format(airport.getLongitude());
            case 3 -> nf3.format(airport.getAltitude());
            case 4 -> nf1.format(airport.getDistance());
            case 5 -> nf0.format(airport.getHeading());
            case 6 ->
                    "<HTML>" + airport.getRunways().stream().map(AirportsPanel.RunwayFacilityData::toString).collect(Collectors.joining("<br>")) + "</HTML>";
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

    void replaceValues(List<AirportInfo> newAirports) {
        this.airports.clear();
        this.airports.addAll(newAirports);
        TableModelEvent event = new TableModelEvent(this);
        listeners.forEach(l -> l.tableChanged(event));
    }

    public void addDetails(AirportsPanel.AirportFacilityData airportFacilityData) {
        String icao = airportFacilityData.getIcao();
        List<AirportInfo> airportInfos = this.airports;
        for (int i = 0; i < airportInfos.size(); i++) {
            AirportInfo airport = airportInfos.get(i);
            if (airport.getIcao().equalsIgnoreCase(icao)) {
                airport.setName(airportFacilityData.getName());
                airportFacilityData.getRunways().forEach(airport::addRunway);
                TableModelEvent event = new TableModelEvent(this, i, i, ALL_COLUMNS, TableModelEvent.UPDATE);
                listeners.forEach(listeners -> listeners.tableChanged(event));
                break;
            }
        }
    }
}