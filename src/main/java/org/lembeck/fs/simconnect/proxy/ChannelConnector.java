package org.lembeck.fs.simconnect.proxy;

import org.lembeck.fs.simconnect.request.SimRequest;
import org.lembeck.fs.simconnect.response.SimResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class ChannelConnector implements Runnable {

    private final SocketChannel sourceChannel;

    private final SocketChannel targetChannel;

    private final PrintStream debugStream;

    enum Direction {
        REQUEST,
        RESPONSE
    }

    private final Direction direction;

    private ChannelConnector(String name, SocketChannel sourceChannel, SocketChannel targetChannel, Direction direction, PrintStream debugStream) {
        this.sourceChannel = Objects.requireNonNull(sourceChannel);
        this.targetChannel = Objects.requireNonNull(targetChannel);
        this.debugStream = debugStream;
        this.direction = direction;
    }

    public static void create(String name, SocketChannel sourceChannel, SocketChannel targetChannel, Direction direction, PrintStream debugStream) {
        ChannelConnector connector = new ChannelConnector(name, sourceChannel, targetChannel, direction, debugStream);
        new Thread(connector, name).start();
    }

    @Override
    public void run() {
        final ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int bytesRead = 0;
        try {
            System.out.println(sourceChannel.isBlocking());
            while (true) {
                buffer.limit(4);
                bytesRead = sourceChannel.read(buffer);
                if (bytesRead == -1) {
                    sourceChannel.close();
                    targetChannel.close();
                    return;
                }

                if (bytesRead != 4) {
                    throw new RuntimeException("Die Länge des Pakets konnte nicht gelesen werden: " + bytesRead);
                }
                final int size = buffer.getInt(0);
                buffer.position(4);
                buffer.limit(size);
                while (bytesRead < size) {
                    bytesRead += sourceChannel.read(buffer);
                }

                buffer.position(0);
                buffer.mark();
                if (direction == Direction.REQUEST) {
                    SimRequest request = SimRequest.parseRequest(buffer);
                    synchronized (debugStream) {
                        debugStream.println(request);
                    }
                } else {
                    SimResponse response = SimResponse.parseResponse(buffer);
                    synchronized (debugStream) {
                        debugStream.println(response);
                    }
                }

                buffer.reset();
                buffer.limit(bytesRead);
                targetChannel.write(buffer);
                buffer.clear();
            }
        } catch (AsynchronousCloseException ace) {
            // ignore
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}