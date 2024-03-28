package org.lembeck.fs.simconnect;


import org.lembeck.fs.simconnect.request.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class MySimConnect {

    private SocketChannel channel;

    private final ResponseReceiver responseReceiver;

    private final ByteBuffer inBuffer;

    private final ByteBuffer outBuffer;

    private final AtomicInteger lastRequestIdentifier = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        MySimConnect mySimConnect = new MySimConnect();
        mySimConnect.connect("192.168.0.170", 26011, "MySimConnect");
    }

    public MySimConnect() {
        responseReceiver = new ResponseReceiver();
        this.inBuffer = ByteBuffer.allocateDirect(64 * 1024);
        this.outBuffer = ByteBuffer.allocateDirect(64 * 1024);
        inBuffer.order(LITTLE_ENDIAN);
        outBuffer.order(LITTLE_ENDIAN);
    }

    public void connect(String hostname, int port, String appName) throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(hostname, port));
        responseReceiver.setChannel(channel);
        Thread thread = new Thread(responseReceiver);
        thread.setDaemon(false);
        thread.setName("Simconnect Request Receiver");
        thread.start();
        write(new HelloRequest(appName));
    }

    public void close() {
        try {
            channel.close();
        } catch (IOException ioe) {
            // ignore
        }
    }

    public ResponseReceiver getRequestReceiver() {
        return responseReceiver;
    }

    public <T extends SimRequest> T write(T request) throws IOException {
        request.setIdentifier(lastRequestIdentifier.incrementAndGet());
        synchronized (outBuffer) {
            outBuffer.clear();
            request.write(outBuffer);
            outBuffer.flip();
            channel.write(outBuffer);
        }
        return request;
    }

    public MapClientEventToSimEventRequest mapClientEventToSimEvent(int eventID, String eventName) throws IOException {
        return write(new MapClientEventToSimEventRequest(eventID, eventName));
    }

    public TransmitClientEventRequest transmitClientEvent(int objectID, int clientEventID, int data, int notificationGroupID, int eventFlag) throws IOException {
        return write(new TransmitClientEventRequest(objectID, clientEventID, data, notificationGroupID, eventFlag));
    }

    public SetSystemEventStateRequest setSystemEventState(int clientEventID, State state) throws IOException {
        return write(new SetSystemEventStateRequest(clientEventID, state));
    }

    public AddClientEventToNotificationGroupRequest addClientEventToNotificationGroup(int notificationGroupID, int clientEventID, boolean maskable) throws IOException {
        return write(new AddClientEventToNotificationGroupRequest(notificationGroupID, clientEventID, maskable));
    }

    public RemoveClientEventRequest removeClientEvent(int notificationGroupID, int clientEventID) throws IOException {
        return write(new RemoveClientEventRequest(notificationGroupID, clientEventID));
    }

    public SetNotificationGroupPriorityRequest stNotificationGroupPriority(int notificationGroupID, int priority) throws IOException {
        return write(new SetNotificationGroupPriorityRequest(notificationGroupID, priority));
    }

    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID, SystemEventName eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID, String eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public UnsubscribeFromSystemEventRequest unsubscribeFromSystemEvent(int eventID) throws IOException {
        return write(new UnsubscribeFromSystemEventRequest(eventID));
    }

    public RequestSystemStateRequest requestSystemState(int requestID, String state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    public RequestSystemStateRequest requestSystemState(int requestID, SystemState state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    public RequestFacilitesListRequest requestFacilitiesList(FacilityListType facilityListType, int requestId) throws IOException {
        return write(new RequestFacilitesListRequest(facilityListType, requestId));
    }

    public SubscribeToFacilitiesRequest subscribeToFacilities(FacilityListType facilityListType, int requestId) throws IOException {
        return write(new SubscribeToFacilitiesRequest(facilityListType, requestId));
    }

    public UnsubscribeToFacilitiesRequest unsubscribeToFacilities(FacilityListType facilityListType) throws IOException {
        return write(new UnsubscribeToFacilitiesRequest(facilityListType));
    }
}