package org.lembeck.fs.simconnect.examples;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.response.RecvSystemStateResponse;

import java.io.IOException;

public class SystemState {

    public static void main(String[] args) throws IOException {
        new SystemState().run();
    }

    private void run() throws IOException {
        SimConnect simConnect = new SimConnect();
        simConnect.getRequestReceiver().addSystemStateHandler(this::handleSystemState);
        simConnect.connect("localhost", 26010, "SystemState");
        simConnect.requestSystemState(1, org.lembeck.fs.simconnect.SystemState.AIRCRAFT_LOADED);
        simConnect.requestSystemState(2, org.lembeck.fs.simconnect.SystemState.DIALOG_MODE);
        simConnect.requestSystemState(3, org.lembeck.fs.simconnect.SystemState.FLIGHT_LOADED);
        simConnect.requestSystemState(4, org.lembeck.fs.simconnect.SystemState.FLIGHT_PLAN);
        simConnect.requestSystemState(5, org.lembeck.fs.simconnect.SystemState.SIM);
    }

    private void handleSystemState(RecvSystemStateResponse recvSystemStateResponse) {
        switch (recvSystemStateResponse.getRequestID()) {
            case 1:
                System.out.printf("Loaded aircraft is '%s'%n", recvSystemStateResponse.getStringValue());
                break;
            case 2:
                System.out.printf("Sim is in dialog mode: '%s'%n", recvSystemStateResponse.getIntValue() == 1);
                break;
            case 3:
                System.out.printf("Loaded flight is '%s'%n", recvSystemStateResponse.getStringValue());
                break;
            case 4:
                System.out.printf("Flight plan (if exists) is '%s'%n", recvSystemStateResponse.getStringValue());
                break;
            case 5:
                System.out.printf("User is controlling the aircraft: '%s'%n", recvSystemStateResponse.getIntValue() == 1);
                break;
        }
    }
}