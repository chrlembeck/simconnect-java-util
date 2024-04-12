package org.lembeck.fs.simconnect.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Simple proxy server for simconnect communications. Can be used to log all traffic between a simconnect client and the
 * simulator.
 */
public class SimDebugProxy {

    /**
     * Main method. Starts the debug proxy.
     *
     * @param args Array of arguments. should be filled with the three elements 'listening Port', 'servername' and 'server port'.
     */
    public static void main(String[] args) throws Exception {
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

    private final int listeningPort;

    private final SocketAddress simulatorAddress;

    /**
     * Creates a new debug proxy.
     *
     * @param listeningPort    Port this proxy is listening on this machine.
     * @param simulatorAddress Network address of the server, the proxy should forward the requests to.
     */
    public SimDebugProxy(int listeningPort, SocketAddress simulatorAddress) {
        this.listeningPort = listeningPort;
        this.simulatorAddress = simulatorAddress;
    }

    private static void printHelp() {
        System.out.printf("""
                Use %1$s with three arguments 'listening Port', 'simulator adress' and 'simulator port'.
                For example: java %1$s 26010 simserver 5010""", SimDebugProxy.class.getSimpleName());
        System.exit(-1);
    }

    /**
     * Creates and opens a new server socket and starts listening for incoming connections.
     */
    public void startServer() throws Exception {
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