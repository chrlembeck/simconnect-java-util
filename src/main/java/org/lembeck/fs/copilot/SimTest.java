package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.request.DataType;
import org.lembeck.fs.simconnect.request.SimconnectPeriod;
import org.lembeck.fs.simconnect.response.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimTest {

    private final SimConnect msc;

    private boolean inSim = false;

    private double userLat;

    private double userLon;

    private double userAlt;

    public SimTest() throws IOException {
        msc = new SimConnect();
        msc.getRequestReceiver().addExceptionHandler(this::handleException);
        msc.getRequestReceiver().addEventHandler(this::handleEvent);
        msc.getRequestReceiver().addOpenHandler(this::handleOpen);
        msc.getRequestReceiver().addAirportListHandler(this::handleAirportList);
        msc.getRequestReceiver().addSimobjectDataHandler(this::handleSimObject);

        msc.connect("192.168.0.170", 26010, "NearestAirports");

        msc.subscribeToSystemEvent(1, "Sim");
        msc.subscribeToSystemEvent(2, "4sec");

        msc.addToDataDefinition(1, "PLANE LATITUDE", "DEGREES", DataType.FLOAT64, 0);
        msc.addToDataDefinition(1, "PLANE LONGITUDE", "DEGREES", DataType.FLOAT64, 0);
        msc.addToDataDefinition(1, "PLANE ALTITUDE", "FEET", DataType.FLOAT64, 0);

        msc.requestDataOnSimObject(1, 1, 0, SimconnectPeriod.SIM_FRAME, 0, 0, 0, 0);

    }

    public void handleEvent(RecvEventResponse e) {
        if (e.getEventID() == 1) {
            inSim = e.getData() == 1;
        } else if (e.getEventID() == 2 && inSim) {
            try {
                msc.requestFacilitiesList(org.lembeck.fs.simconnect.request.FacilityListType.AIRPORT, 3);
            } catch (IOException e1) {
            }
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
            return icao + " (" + df.format(dist / 1000.0) + " nm)";
        }

        public int compareTo(Airport o) {
            return (int) Math.signum(dist - o.dist);
        }
    }

    List<Airport> airports = new ArrayList<>();

    public void handleAirportList(RecvAirportListResponse list) {
        if (list.getEntryNumber() == 0) {
            airports.clear();
        }
        System.out.println(list.getEntryNumber() + "/" + list.getOutOf());

        for (FacilityAirport fa : list.getAirportList()) {

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
        if (list.getEntryNumber() == list.getOutOf() - 1) {
            Collections.sort(airports);
            int n = Math.min(10, airports.size());
            String[] array = new String[n];
            for (int i = 0; i < n; i++) {
                array[i] = airports.get(i).toString();
            }
            System.out.println(Arrays.toString(array));
        }
    }

    public void handleSimObject(RecvSimobjectDataResponse response) {
        ByteBuffer data = response.getData();
        userLat = data.getDouble();
        userLon = data.getDouble();
        userAlt = data.getDouble();
        System.out.println(userLat + " " + userLon + " " + userAlt);
    }

    public void handleException(RecvExceptionResponse e) {
        System.out.println("Exception : " + e);
    }

    public void handleOpen(RecvOpenResponse e) {
        System.out.println("Connected to " + e.getApplicationName());
    }

    public static void main(String[] args) throws Exception {
        SimTest nn = new SimTest();
    }

    /**
     * Distance in METERS between points with lat/lon in DEGREES
     *
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
                Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2) +
                        Math.sin(lat1) * Math.sin(lat2));
    }

    // in METERS
    public static final double RADIUS_EARTH_M = 6378137;//	6367176.0

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

        double b = Math.atan2(Math.sin(lon2 - lon1) * Math.cos(lat2),
                Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        if (b < 0) b = Math.PI * 2 + b;

        return b;
    }

    /**
     * Heading in RADIANS between two points in RADIANS
     *
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

        double b = Math.atan2(Math.sin(lon2 - lon1) * Math.cos(lat2),
                Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
        if (b < 0) b = Math.PI * 2 + b;

        return b;
    }
}