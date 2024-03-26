package org.lembeck.fs.copilot;

import flightsim.simconnect.FacilityListType;
import flightsim.simconnect.SimConnect;
import flightsim.simconnect.SimConnectDataType;
import flightsim.simconnect.SimConnectPeriod;
import flightsim.simconnect.recv.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimTest implements OpenHandler, ExceptionHandler, EventHandler, FacilitiesListHandler, SimObjectDataHandler {

    private final SimConnect sc;
    private final DispatcherTask dt;

    private boolean inSim = false;

    private double userLat;

    private double userLon;

    private double userAlt;

    public SimTest() throws IOException {
        sc = new SimConnect("NearestAirports", "192.168.0.170", 26010);

        sc.subscribeToSystemEvent(1, "Sim");
        sc.subscribeToSystemEvent(2, "4sec");

        sc.addToDataDefinition(1, "PLANE LATITUDE", "DEGREES", SimConnectDataType.FLOAT64);
        sc.addToDataDefinition(1, "PLANE LONGITUDE", "DEGREES", SimConnectDataType.FLOAT64);
        sc.addToDataDefinition(1, "PLANE ALTITUDE", "FEET", SimConnectDataType.FLOAT64);

        sc.requestDataOnSimObject(1, 1, 0, SimConnectPeriod.SIM_FRAME);

        dt = new DispatcherTask(sc);
        dt.addOpenHandler(this);
        dt.addExceptionHandler(this);
        dt.addEventHandler(this);
        dt.addFacilitiesListHandler(this);
        dt.addSimObjectDataHandler(this);

    }

    public void start(){
        dt.createThread().start();
    }

    public void handleEvent(SimConnect sender, RecvEvent e) {
        if (e.getEventID() == 1) {
            inSim = e.getData() == 1;
        } else if (e.getEventID() == 2 && inSim){
            try {
                sender.requestFacilitiesList(FacilityListType.AIRPORT, 3);
            } catch (IOException e1) {}
        }
    }
    private static final DecimalFormat df = new DecimalFormat("###.0");

    class Airport implements Comparable<Airport> {
        String icao;

        double latitude;

        double longitude;
        double dist;
        double hdg;

        double altitude;

        @Override
        public String toString() {
            return icao +" (" + df.format(dist/1000.0) + " nm)";
        }

        public int compareTo(Airport o) {
            return (int) Math.signum(dist - o.dist);
        }
    }

    List<Airport> airports = new ArrayList<>();

    public void handleAirportList(SimConnect sender, RecvAirportList list) {
        if (list.getEntryNumber() == 0) {
            airports.clear();
        }
        System.out.println(list.getEntryNumber() + "/" + list.getOutOf());

        for (FacilityAirport fa : list.getFacilities()) {

            if (userLat != 0 && userLon != 0) {
                double apLat = fa.getLatitude();
                double apLon = fa.getLongitude();
                double dist = distance(userLat, userLon, apLat, apLon);
                double hdg = heading(userLat, userLon, apLat, apLon);


                Airport ap = new Airport();
                ap.icao = fa.getIcao();
                ap.dist = dist;
                ap.hdg = hdg;
                ap.latitude = apLat;
                ap.longitude = apLon;
                ap.altitude = fa.getAltitude();
                airports.add(ap);

            }
        }
        if (airports.isEmpty()) return;
        Collections.sort(airports);
        int n = Math.min(6, airports.size());
        String [] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = airports.get(i).toString();
        }
        System.out.println(Arrays.toString(array));
    }

    public void handleSimObject(SimConnect sender, RecvSimObjectData e) {
        userLat = e.getDataFloat64();
        userLon = e.getDataFloat64();
        userAlt = e.getDataFloat64();
        System.out.println(userLat + " " + userLon + " " + userAlt);
    }

    public void handleNDBList(SimConnect sender, RecvNDBList list) {
    }
    public void handleVORList(SimConnect sender, RecvVORList list) {
    }
    public void handleWaypointList(SimConnect sender, RecvWaypointList list) {
    }


    public void handleException(SimConnect sender, RecvException e) {
        System.out.println("Exception : " +e.getException());
    }

    public void handleOpen(SimConnect sender, RecvOpen e) {
        System.out.println("Connected to " + e.getApplicationName());
    }

    public static void main(String[] args) throws Exception {
        SimTest nn = new SimTest();
        nn.start();
    }

    /**
     * Distance in METERS between points with lat/lon in DEGREES
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        return RADIUS_EARTH_M *
                Math.acos(Math.cos(lat1) * 	Math.cos(lat2) * Math.cos(lon1-lon2) +
                        Math.sin(lat1)*Math.sin(lat2));
    }

    // in METERS
    public static final double RADIUS_EARTH_M	=	6378137;//	6367176.0

    /**
     * Heading in RADIANS between two points in DEGREES (sorry)
     */
    public static double heading(double lat1, double lon1, double lat2, double lon2) {
        double h;

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        if (Math.cos(lat1) < 0.0001) {
            // prevent overflow
            if (lat1 > 0) h = 180;
            else h = 0;

            return h;
        }

        double b = Math.atan2(Math.sin(lon2-lon1) * Math.cos(lat2), Math.cos(lat1)*Math.sin(lat2) -
                Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        if (b < 0) b = Math.PI * 2 + b;

        return b;
    }

    /**
     * Heading in RADIANS between two points in RADIANS
     * @param p1
     * @param p2
     * @return
     */
    public static double heading(Point2D p1, Point2D p2) {
        double h;

        double lat1 = p1.x;
        double lon1 = p1.y;
        double lat2 = p2.x;
        double lon2 = p2.y;


        if (Math.cos(lat1) < 0.0001) {
            // prevent overflow
            if (lat1 > 0) h = 180;
            else h = 0;

            return h;
        }

        double b = Math.atan2(Math.sin(lon2-lon1) * Math.cos(lat2), Math.cos(lat1)*Math.sin(lat2) -
                Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        if (b < 0) b = Math.PI * 2 + b;

        return b;
    }}
