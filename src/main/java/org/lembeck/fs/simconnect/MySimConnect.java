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

    public void write(SimRequest request) throws IOException {
        request.setIdentifier(lastRequestIdentifier.incrementAndGet());
        synchronized (outBuffer) {
            outBuffer.clear();
            request.write(outBuffer);
            outBuffer.flip();
            channel.write(outBuffer);
        }
    }


    public void subscribeToSystemEvent(int eventID, SystemEventName eventName) throws IOException {
        write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public void subscribeToSystemEvent(int eventID, String eventName) throws IOException {
        write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public void unsubscribeFromSystemEvent(int eventID) throws IOException {
        write(new UnsubscribeFromSystemEventRequest(eventID));
    }

    public void requestSystemState(int requestID, String state) throws IOException {
        write(new RequestSystemStateRequest(requestID, state));
    }

    public void requestSystemState(int requestID, SystemState state) throws IOException {
        write(new RequestSystemStateRequest(requestID, state));
    }

    public void requestFacilitiesList(FacilityListType facilityListType, int requestId) throws IOException {
        write(new RequestFacilitesListRequest(facilityListType, requestId));
    }

    public void subscribeToFacilities(FacilityListType facilityListType, int requestId) throws IOException {
        write(new SubscribeToFacilitiesRequest(facilityListType, requestId));
    }

    public void unsubscribeToFacilities(FacilityListType facilityListType) throws IOException {
        write(new UnsubscribeToFacilitiesRequest(facilityListType));
    }
}