package org.lembeck.fs.copilot.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import static org.lembeck.fs.copilot.proxy.ChannelConnector.Direction.REQUEST;
import static org.lembeck.fs.copilot.proxy.ChannelConnector.Direction.RESPONSE;

public class SocketHandler implements Runnable {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final SocketChannel clientSocketChannel;

    private final SocketChannel serverSocketChannel;

    private SocketHandler(SocketChannel clientSocketChannel) throws IOException {
        this.clientSocketChannel = clientSocketChannel;
        this.serverSocketChannel = SocketChannel.open(new InetSocketAddress("192.168.0.170", 26011));
    }

    public static void create(SocketChannel socketChannel) throws IOException{
        SocketHandler handler = new SocketHandler(socketChannel);
        Thread thread = new Thread(handler, "Socket Handler " + counter.incrementAndGet());
        thread.setDaemon(false);
        thread.start();
    }

    @Override
    public void run() {
        ChannelConnector.create(Thread.currentThread().getName() + " request", clientSocketChannel, serverSocketChannel, REQUEST);
        ChannelConnector.create(Thread.currentThread().getName() + " response", serverSocketChannel, clientSocketChannel, RESPONSE);
    }
}
