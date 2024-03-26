package org.lembeck.fs.simconnect.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SimDebugProxy {

    private boolean running;

    public static void main(String[] args) throws Exception{
        new SimDebugProxy().startServer();
    }

    public void startServer() throws Exception{
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(26010));
        System.out.println("proxy listening on port 26010");
        running = true;
        while (running) {
            SocketChannel socketChannel = server.accept();
            System.out.println("new connection requested.");
            connectionOpened(socketChannel);
        }
    }

    private void connectionOpened(SocketChannel socketChannel) throws IOException {
        SocketHandler.create(socketChannel);
    }
}