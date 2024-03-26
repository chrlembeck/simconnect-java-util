package org.lembeck.fs.simconnect.examples;

import org.lembeck.fs.simconnect.MySimConnect;
import org.lembeck.fs.simconnect.request.FacilityListType;
import org.lembeck.fs.simconnect.response.FacilityAirport;
import org.lembeck.fs.simconnect.response.RecvAirportListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadAllAirports {

    private final MySimConnect simConnect = new MySimConnect();

    private final List<FacilityAirport> airports = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ReadAllAirports().run();
    }

    public void run() throws IOException {
        simConnect.getRequestReceiver().addAirportListHandler(this::handleAirports);
        simConnect.connect("localhost", 26010, "ReadAllAirports");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        simConnect.requestFacilitiesList(FacilityListType.AIRPORT, 1);
    }

    public void handleAirports(RecvAirportListResponse response) {
        Arrays.stream(response.getAirportList()).forEach(airports::add);
        if (response.getEntryNumber() == response.getOutOf() - 1) {
            System.out.println(airports.stream().map(FacilityAirport::getIcao).sorted().collect(Collectors.joining(", ")));
            System.out.println("Airport count: " + airports.size() + ", Response count: " + response.getOutOf());

            simConnect.close();
        }
    }
}