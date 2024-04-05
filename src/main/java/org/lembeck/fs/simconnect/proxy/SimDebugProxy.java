package org.lembeck.fs.simconnect.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SimDebugProxy {

    private final int listeningPort;

    private final SocketAddress simulatorAddress;

    public SimDebugProxy(int listeningPort, SocketAddress simulatorAddress) {
        this.listeningPort = listeningPort;
        this.simulatorAddress = simulatorAddress;
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 3) {
            printHelp();
        }
        try {
            int lPort = Integer.parseInt(args[0]);
            String servername = args[1];
            int sPort = Integer.parseInt(args[2]);
            new SimDebugProxy(lPort, new InetSocketAddress(servername, sPort)).startServer();
        } catch (NumberFormatException nfe) {
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.printf("""
                Use %1$s with three arguments 'listening Port', 'simulator adress' and 'simulator port'.
                For example: java %1$s 26010 simserver 5010""", SimDebugProxy.class.getSimpleName());
        System.exit(-1);
    }

    public void startServer() throws Exception{
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            server.bind(new InetSocketAddress(listeningPort));
            System.out.println("proxy listening on port 26010");
            while (true) {
                SocketChannel socketChannel = server.accept();
                System.out.println("new connection requested.");
                connectionOpened(socketChannel);
            }
        }
    }

    private void connectionOpened(SocketChannel socketChannel) throws IOException {
        SocketHandler.create(socketChannel, simulatorAddress);
    }
}