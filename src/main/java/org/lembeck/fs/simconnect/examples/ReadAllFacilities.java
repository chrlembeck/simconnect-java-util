package org.lembeck.fs.simconnect.examples;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.constants.FacilityListType;
import org.lembeck.fs.simconnect.response.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadAllFacilities {

    public String hostname = "localhost";

    public int port = 26010;

    private final SimConnect simConnect = new SimConnect();

    private final List<FacilityAirport> airports = new ArrayList<>();
    private final List<FacilityWaypoint> waypoints = new ArrayList<>();
    private final List<FacilityNDB> ndbs = new ArrayList<>();
    private final List<FacilityVOR> vors = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ReadAllFacilities().run();
    }

    public void run() throws IOException {
        simConnect.getRequestReceiver().addAirportListHandler(this::handleAirports);
        simConnect.getRequestReceiver().addWaypointListHandler(this::handleWaypoints);
        simConnect.getRequestReceiver().addNdbListHandler(this::handleNDBs);
        simConnect.getRequestReceiver().addVorListHandler(this::handleVORs);
        simConnect.connect(hostname, port, "ReadAllFacilities");

        simConnect.requestFacilitiesList(FacilityListType.AIRPORT, 1);
        simConnect.requestFacilitiesList(FacilityListType.WAYPOINT, 2);
        simConnect.requestFacilitiesList(FacilityListType.NDB, 3);
        simConnect.requestFacilitiesList(FacilityListType.VOR, 4);

        simConnect.subscribeToFacilities(FacilityListType.WAYPOINT, 5);
        simConnect.subscribeToFacilities(FacilityListType.NDB, 6);
        simConnect.subscribeToFacilities(FacilityListType.VOR, 7);
    }

    public void handleAirports(RecvAirportListResponse response) {
        Arrays.stream(response.getAirportList()).forEach(airports::add);
        if (response.getEntryNumber() == response.getOutOf() - 1) {
            System.out.println(airports.stream().map(FacilityAirport::getIcao).sorted().collect(Collectors.joining(", ")));
            System.out.println("Airport count: " + airports.size() + ", Response count: " + response.getOutOf());
        }
    }

    private void handleWaypoints(RecvWaypointListResponse response) {
        Arrays.stream(response.getWaypointList()).forEach(waypoints::add);
        if (response.getEntryNumber() == response.getOutOf() - 1) {
            System.out.println(waypoints.stream().map(FacilityWaypoint::getIcao).sorted().collect(Collectors.joining(", ")));
            System.out.println("Waypoint count: " + waypoints.size() + ", Response count: " + response.getOutOf());
        }
    }

    private void handleNDBs(RecvNdbListResponse response) {
        Arrays.stream(response.getNdbList()).forEach(ndbs::add);
        if (response.getEntryNumber() == response.getOutOf() - 1) {
            System.out.println(ndbs.stream().map(FacilityNDB::getIcao).sorted().collect(Collectors.joining(", ")));
            System.out.println("NDB count: " + ndbs.size() + ", Response count: " + response.getOutOf());
        }
    }

    private void handleVORs(RecvVorListResponse response) {
        Arrays.stream(response.getVorList()).forEach(vors::add);
        if (response.getEntryNumber() == response.getOutOf() - 1) {
            System.out.println(vors.stream().map(FacilityVOR::getIcao).sorted().collect(Collectors.joining(", ")));
            System.out.println("VOR count: " + vors.size() + ", Response count: " + response.getOutOf());
            System.out.println(vors.stream().filter(vor -> vor.getIcao().equalsIgnoreCase("OSN")).findFirst().get());
        }
    }
}