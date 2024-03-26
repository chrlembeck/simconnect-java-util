package org.lembeck.fs.simconnect;


import org.lembeck.fs.simconnect.request.FacilityListType;
import org.lembeck.fs.simconnect.request.HelloRequest;
import org.lembeck.fs.simconnect.request.RequestFacilitesListRequest;
import org.lembeck.fs.simconnect.request.SimRequest;

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

    public void write(SimRequest request) throws IOException {
        request.setIdentifier(lastRequestIdentifier.incrementAndGet());
        synchronized (outBuffer) {
            outBuffer.clear();
            request.write(outBuffer);
            outBuffer.flip();
            channel.write(outBuffer);
        }
    }

    public void requestFacilitiesList(FacilityListType facilityListType, int requestId) throws IOException {
        write(new RequestFacilitesListRequest(facilityListType, requestId));
    }

    public ResponseReceiver getRequestReceiver() {
        return responseReceiver;
    }
}