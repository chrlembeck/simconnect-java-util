package org.lembeck.fs.simconnect.proxy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import static org.lembeck.fs.simconnect.proxy.ChannelConnector.Direction.REQUEST;
import static org.lembeck.fs.simconnect.proxy.ChannelConnector.Direction.RESPONSE;

public class SocketHandler implements Runnable {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final SocketChannel clientSocketChannel;

    private final SocketChannel serverSocketChannel;

    private final PrintStream debugStream;

    private SocketHandler(SocketChannel clientSocketChannel, PrintStream debugStream) throws IOException {
        this.clientSocketChannel = clientSocketChannel;
        this.serverSocketChannel = SocketChannel.open(new InetSocketAddress("192.168.0.170", 26011));
        this.debugStream = debugStream;
    }

    public static void create(SocketChannel socketChannel) throws IOException{
        SocketHandler handler = new SocketHandler(socketChannel, System.out);
        Thread thread = new Thread(handler, "Socket Handler " + counter.incrementAndGet());
        thread.setDaemon(false);
        thread.start();
    }

    @Override
    public void run() {
        ChannelConnector.create(Thread.currentThread().getName() + " request", clientSocketChannel, serverSocketChannel, REQUEST, debugStream);
        ChannelConnector.create(Thread.currentThread().getName() + " response", serverSocketChannel, clientSocketChannel, RESPONSE, debugStream);
    }
}
