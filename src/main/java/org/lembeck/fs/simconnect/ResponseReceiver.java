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
    private final HandlerList<ExceptionHandler, RecvExceptionResponse> exceptionHandlers = new HandlerList<>((handler, response) -> handler.handleException(response));
    private final HandlerList<EventHandler, RecvEventResponse> eventHandlers = new HandlerList<>((handler, response) -> handler.handleEvent(response));
    private final HandlerList<EventFilenameHandler, RecvEventFilenameResponse> eventFilenameHandlers = new HandlerList<>((handler, response) -> handler.handleEventFilename(response));
    private final HandlerList<SystemStateHandler, RecvSystemStateResponse> systemStateHandlers = new HandlerList<>((handler, response) -> handler.handleSystemState(response));
    private final HandlerList<AirportListHandler, RecvAirportListResponse> airportListHandlers = new HandlerList<>((handler, response) -> handler.hanldeAirportList(response));
    private final HandlerList<WaypointListHandler, RecvWaypointListResponse> waypointListHandlers = new HandlerList<>((handler, response) -> handler.hanldeWaypointList(response));
    private final HandlerList<NdbListHandler, RecvNdbListResponse> ndbListHandlers = new HandlerList<>((handler, response) -> handler.hanldeNdbList(response));
    private final HandlerList<VorListHandler, RecvVorListResponse> vorListHandlers = new HandlerList<>((handler, response) -> handler.hanldeVorList(response));
    private final HandlerList<OpenHandler, RecvOpenResponse> openHandlers = new HandlerList<>((handler, response) -> handler.handleOpen(response));
    private final HandlerList<QuitHandler, RecvQuitResponse> quitHandlers = new HandlerList<>((handler, response) -> handler.handleQuit(response));
    private final HandlerList<SimobjectDataHandler, RecvSimobjectDataResponse> simobjectDataHandlers = new HandlerList<>((handler, response) -> handler.handleSimobjectData(response));
    private final HandlerList<SimobjectDataByTypeHandler, RecvSimobjectDataByTypeResponse> simobjectDataByTypeHandlers = new HandlerList<>((handler, response) -> handler.handleSimobjectDataByType(response));

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
            case RecvExceptionResponse r: // 01
                exceptionHandlers.notifyHandlers(r);
                break;
            case RecvOpenResponse r: // 02
                openHandlers.notifyHandlers(r);
                break;
            case RecvQuitResponse r: // 03
                quitHandlers.notifyHandlers(r);
                break;
            case RecvEventObjectAddRemoveResponse r: // 05
                break;
            case RecvEventFilenameResponse r: // 06
                eventFilenameHandlers.notifyHandlers(r);
                break;
            case RecvEventFrameResponse r: //07
                break;
            case RecvEventMultiplayerServerStartedResponse r: //16
                break;
            case RecvEventMultiplayerClientStartedResponse r: //17
                break;
            case RecvEventMultiplayerSessionEndedResponse r: //18
                break;
            case RecvEventResponse r: // 04
                eventHandlers.notifyHandlers(r);
                break;
            case RecvSimobjectDataByTypeResponse r: // 09
                simobjectDataByTypeHandlers.notifyHandlers(r);
                break;
            case RecvClientDataResponse r: //10
                break;
            case RecvSimobjectDataResponse r: // 08
                simobjectDataHandlers.notifyHandlers(r);
                break;
            case RecvAssignedObjectIdResponse r: //0c
                break;
            case RecvReservedKeyResponse r: //0d
                break;
            case RecvSystemStateResponse r: // 0f
                systemStateHandlers.notifyHandlers(r);
                break;
            case RecvAirportListResponse r: // 12
                airportListHandlers.notifyHandlers(r);
                break;
            case RecvVorListResponse r: // 13
                vorListHandlers.notifyHandlers(r);
                break;
            case RecvNdbListResponse r: // 14
                ndbListHandlers.notifyHandlers(r);
                break;
            case RecvWaypointListResponse r: // 15
                waypointListHandlers.notifyHandlers(r);
                break;
            case RecvEventEx1Response r: // 1b
                break;
            case RecvFacilityDataResponse r: // 1c
                break;
            case RecvFacilityDataEndResponse r: // 1d
                break;
            case RecvJetwayDataResponse r: // 1f
                break;
            case RecvControllersListResponse r: // 20
                break;
            case RecvEnumerateInputEventsResponse r: // 22
                break;

            default:
                break;
        }
    }

    public void addExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandlers.addHandler(exceptionHandler);
    }

    public void addEventHandler(EventHandler eventHandler) {
        this.eventHandlers.addHandler(eventHandler);
    }

    public void addEventFilenameHandler(EventFilenameHandler eventFilenameHandler) {
        this.eventFilenameHandlers.addHandler(eventFilenameHandler);
    }

    public void addSystemStateHandler(SystemStateHandler systemStateHandler) {
        this.systemStateHandlers.addHandler(systemStateHandler);
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

    public void addOpenHandler(OpenHandler openHandler) {
        this.openHandlers.addHandler(openHandler);
    }

    public void addSimobjectDataHandler(SimobjectDataHandler simobjectDataHandler) {
        this.simobjectDataHandlers.addHandler(simobjectDataHandler);
    }

    public void addSimobjectDataByTypeHandler(SimobjectDataByTypeHandler simobjectDataByTypeHandler) {
        this.simobjectDataByTypeHandlers.addHandler(simobjectDataByTypeHandler);
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