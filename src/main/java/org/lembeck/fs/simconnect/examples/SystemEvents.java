package org.lembeck.fs.simconnect.examples;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.constants.SystemEventName;
import org.lembeck.fs.simconnect.response.RecvEventFilenameResponse;
import org.lembeck.fs.simconnect.response.RecvEventResponse;
import java.io.IOException;

public class SystemEvents {

    public String hostname = "localhost";

    public int port = 26010;

    int counter = 0;

    private SimConnect simConnect;

    public static void main(String[] args) throws IOException {
        new SystemEvents().run();
    }

    private void run() throws IOException {
        simConnect = new SimConnect();
        simConnect.getRequestReceiver().addEventHandler(this::handleEvent);
        simConnect.getRequestReceiver().addEventFilenameHandler(this::handleEventFilename);
        simConnect.connect(hostname, port, "SystemEvents");
        simConnect.subscribeToSystemEvent(1, SystemEventName.PAUSE);
        simConnect.subscribeToSystemEvent(2, SystemEventName.FOUR_SEC);
        simConnect.subscribeToSystemEvent(3, SystemEventName.FLIGHT_LOADED);
        simConnect.subscribeToSystemEvent(4, SystemEventName.FLIGHT_SAVED);

    }

    private void handleEvent(RecvEventResponse recvEventResponse) {
        switch (recvEventResponse.getEventID()) {
            case 1:
                System.out.println("Simulator is " + (recvEventResponse.getData() == 1 ? "paused" : "unpaused"));
                break;
            case 2:
                System.out.println("This event will be sent every 4 seconds.");
                counter++;
                if (counter == 5) {
                    try {
                        System.out.println("Unsubscribe from 4 seconds event...");
                        simConnect.unsubscribeFromSystemEvent(2);
                    } catch (IOException ioe) {/*ignore*/}
                }
                break;
        }
    }

    private void handleEventFilename(RecvEventFilenameResponse recvEventFilenameResponse) {
        switch (recvEventFilenameResponse.getEventID()) {
            case 3:
                System.out.printf("Flight loaded from file '%s'%n", recvEventFilenameResponse.getFilename());
                break;
            case 4:
                System.out.printf("Flight saved to file '%s'%n", recvEventFilenameResponse.getFilename());
                break;
        }
    }
}