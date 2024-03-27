package org.lembeck.fs.simconnect;

import org.lembeck.fs.simconnect.handler.*;
import org.lembeck.fs.simconnect.response.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ResponseReceiver implements Runnable {

    private SocketChannel channel;

    private final HandlerList<ResponseHandler, SimResponse> responseHandlers = new HandlerList<>((handler, response) -> handler.handleResponse(response));
    private final HandlerList<AirportListHandler, RecvAirportListResponse> airportListHandlers = new HandlerList<>((handler, response) -> handler.hanldeAirportList(response));
    private final HandlerList<WaypointListHandler, RecvWaypointListResponse> waypointListHandlers = new HandlerList<>((handler, response) -> handler.hanldeWaypointList(response));
    private final HandlerList<NdbListHandler, RecvNdbListResponse> ndbListHandlers = new HandlerList<>((handler, response) -> handler.hanldeNdbList(response));
    private final HandlerList<VorListHandler, RecvVorListResponse> vorListHandlers = new HandlerList<>((handler, response) -> handler.hanldeVorList(response));

    @Override
    public void run() {
        ByteBuffer readBuffer = ByteBuffer.allocate(64 * 1024);
        readBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int bytesRead = 0;
        try {
            while (true) {
                readBuffer.limit(4);
                readBuffer.position(0);
                bytesRead = channel.read(readBuffer);
                if (bytesRead != 4) {
                    throw new RuntimeException("Die Länge des Pakets konnte nicht gelesen werden: " + bytesRead);
                }
                int size = readBuffer.getInt(0);
                readBuffer.position(4);
                readBuffer.limit(size);
                while (bytesRead < size) {
                    bytesRead += channel.read(readBuffer);
                }

                readBuffer.position(0);
                SimResponse response = SimResponse.parseResponse(size, readBuffer);
                handleResponse(response);
            }
        } catch (ClosedChannelException cce) {
            // ignore
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private void handleResponse(SimResponse response) {
        responseHandlers.notifyHandlers(response);
        switch (response) {
            case RecvAirportListResponse r:
                airportListHandlers.notifyHandlers(r);
                break;
            case RecvWaypointListResponse r:
                waypointListHandlers.notifyHandlers(r);
                break;
            case RecvNdbListResponse r:
                ndbListHandlers.notifyHandlers(r);
                break;
            case RecvVorListResponse r:
                vorListHandlers.notifyHandlers(r);
                break;
            default:
                break;
        }
    }

    public void addAirportListHandler(AirportListHandler airportListHandler) {
        this.airportListHandlers.addHandler(airportListHandler);
    }

    public void addWaypointListHandler(WaypointListHandler waypointListHandler) {
        this.waypointListHandlers.addHandler(waypointListHandler);
    }

    public void addNdbListHandler(NdbListHandler ndbListHandler) {
        this.ndbListHandlers.addHandler(ndbListHandler);
    }

    public void addVorListHandler(VorListHandler vorListHandler) {
        this.vorListHandlers.addHandler(vorListHandler);
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

    static class HandlerList<H, R> {

        private final BiConsumer<H, R> handleMethod;
        private final List<H> handlers = new ArrayList<>();

        public HandlerList(BiConsumer<H, R> handleMethod) {
            this.handleMethod = handleMethod;
        }


        public void notifyHandlers(R response) {
            for (int i = handlers.size() - 1; i >= 0; i--) {
                handleMethod.accept(handlers.get(i), response);
            }
        }

        public void addHandler(H handler) {
            handlers.add(handler);
        }
    }
}