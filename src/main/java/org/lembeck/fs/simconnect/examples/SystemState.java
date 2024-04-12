package org.lembeck.fs.simconnect.examples;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.response.RecvSystemStateResponse;
import java.io.IOException;

/**
 * Reads some system states.
 */
public class SystemState {

    private final String hostname = "localhost";

    private final int port = 26010;


    /**
     * Main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) throws IOException {
        new SystemState().run();
    }

    private void run() throws IOException {
        SimConnect simConnect = new SimConnect();
        simConnect.getRequestReceiver().addSystemStateHandler(this::handleSystemState);
        simConnect.connect(hostname, port, "SystemState");
        simConnect.requestSystemState(1, org.lembeck.fs.simconnect.constants.SystemState.AIRCRAFT_LOADED);
        simConnect.requestSystemState(2, org.lembeck.fs.simconnect.constants.SystemState.DIALOG_MODE);
        simConnect.requestSystemState(3, org.lembeck.fs.simconnect.constants.SystemState.FLIGHT_LOADED);
        simConnect.requestSystemState(4, org.lembeck.fs.simconnect.constants.SystemState.FLIGHT_PLAN);
        simConnect.requestSystemState(5, org.lembeck.fs.simconnect.constants.SystemState.SIM);
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