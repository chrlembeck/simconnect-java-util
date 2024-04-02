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
    private final HandlerList<EventFrameHandler, RecvEventFrameResponse> eventFrameHandlers = new HandlerList<>((handler, response) -> handler.handleEventFrame(response));
    private final HandlerList<EventMultiplayerClientStartedHandler, RecvEventMultiplayerClientStartedResponse> eventMultiplayerClientStartedHandlers = new HandlerList<>((handler, response) -> handler.handleEventMultiplayerClientStarted(response));
    private final HandlerList<EventMultiplayerServerStartedHandler, RecvEventMultiplayerServerStartedResponse> eventMultiplayerServerStartedHandlers = new HandlerList<>((handler, response) -> handler.handleEventMultiplayerServerStarted(response));
    private final HandlerList<EventMultiplayerSessionEndedHandler, RecvEventMultiplayerSessionEndedResponse> eventMultiplayerSessionEndedHandlers = new HandlerList<>((handler, response) -> handler.handleEventMultiplayerSessionEnded(response));
    private final HandlerList<ClientDataHandler, RecvClientDataResponse> clientDataHandlers = new HandlerList<>((handler, response) -> handler.handleClientData(response));
    private final HandlerList<AssignedObjectIdHandler, RecvAssignedObjectIdResponse> assignedObjectIdHandlers = new HandlerList<>((handler, response) -> handler.handleAssignedObjectId(response));
    private final HandlerList<ReservedKeyHandler, RecvReservedKeyResponse> reservedKeyHandlers = new HandlerList<>((handler, response) -> handler.handleReservedKey(response));
    private final HandlerList<EventObjectAddRemoveHandler, RecvEventObjectAddRemoveResponse> eventObjectAddRemoveHandlers = new HandlerList<>((handler, response) -> handler.handleEventObjectAddRemove(response));
    private final HandlerList<EventEx1Handler, RecvEventEx1Response> eventEx1Handlers = new HandlerList<>((handler, response) -> handler.handleEventEx1(response));
    private final HandlerList<FacilityDataHandler, RecvFacilityDataResponse> facilityDataHandlers = new HandlerList<>((handler, response) -> handler.handleFacilityData(response));
    private final HandlerList<FacilityDataEndHandler, RecvFacilityDataEndResponse> facilityDataEndHandlers = new HandlerList<>((handler, response) -> handler.handleFacilityDataEnd(response));
    private final HandlerList<JetwayDataHandler, RecvJetwayDataResponse> jetwayDataHandlers = new HandlerList<>((handler, response) -> handler.handleJetwayData(response));
    private final HandlerList<ControllersListHandler, RecvControllersListResponse> controllersListHandlers = new HandlerList<>((handler, response) -> handler.handleControllersList(response));
    private final HandlerList<EnumerateInputEventsHandler, RecvEnumerateInputEventsResponse> enumerateInputEventsHandlers = new HandlerList<>((handler, response) -> handler.handleEnumerateInputEvents(response));

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

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
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
                eventObjectAddRemoveHandlers.notifyHandlers(r);
                break;
            case RecvEventFilenameResponse r: // 06
                eventFilenameHandlers.notifyHandlers(r);
                break;
            case RecvEventFrameResponse r: //07
                eventFrameHandlers.notifyHandlers(r);
                break;
            case RecvEventMultiplayerServerStartedResponse r: //16
                eventMultiplayerServerStartedHandlers.notifyHandlers(r);
                break;
            case RecvEventMultiplayerClientStartedResponse r: //17
                eventMultiplayerClientStartedHandlers.notifyHandlers(r);
                break;
            case RecvEventMultiplayerSessionEndedResponse r: //18
                eventMultiplayerSessionEndedHandlers.notifyHandlers(r);
                break;
            case RecvEventResponse r: // 04
                eventHandlers.notifyHandlers(r);
                break;
            case RecvSimobjectDataByTypeResponse r: // 09
                simobjectDataByTypeHandlers.notifyHandlers(r);
                break;
            case RecvClientDataResponse r: //10
                clientDataHandlers.notifyHandlers(r);
                break;
            case RecvSimobjectDataResponse r: // 08
                simobjectDataHandlers.notifyHandlers(r);
                break;
            case RecvAssignedObjectIdResponse r: //0c
                assignedObjectIdHandlers.notifyHandlers(r);
                break;
            case RecvReservedKeyResponse r: //0d
                reservedKeyHandlers.notifyHandlers(r);
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
                eventEx1Handlers.notifyHandlers(r);
                break;
            case RecvFacilityDataResponse r: // 1c
                facilityDataHandlers.notifyHandlers(r);
                break;
            case RecvFacilityDataEndResponse r: // 1d
                facilityDataEndHandlers.notifyHandlers(r);
                break;
            case RecvJetwayDataResponse r: // 1f
                jetwayDataHandlers.notifyHandlers(r);
                break;
            case RecvControllersListResponse r: // 20
                controllersListHandlers.notifyHandlers(r);
                break;
            case RecvEnumerateInputEventsResponse r: // 22
                enumerateInputEventsHandlers.notifyHandlers(r);
                break;
            default:
                break;
        }
    }

    public void addAssignedObjectIdHandler(AssignedObjectIdHandler handler) {
        assignedObjectIdHandlers.addHandler(handler);
    }

    public void removeAssignedObjectIdHandler(AssignedObjectIdHandler handler) {
        assignedObjectIdHandlers.removeHandler(handler);
    }

    public void addClientDataHandler(ClientDataHandler handler) {
        clientDataHandlers.addHandler(handler);
    }

    public void removeClientDataHandler(ClientDataHandler handler) {
        clientDataHandlers.removeHandler(handler);
    }

    public void addControllersListHandler(ControllersListHandler handler) {
        controllersListHandlers.addHandler(handler);
    }

    public void removeControllersListHandler(ControllersListHandler handler) {
        controllersListHandlers.removeHandler(handler);
    }

    public void addEnumerateInputEventsHandler(EnumerateInputEventsHandler handler) {
        enumerateInputEventsHandlers.addHandler(handler);
    }

    public void removeEnumerateInputEventsHandler(EnumerateInputEventsHandler handler) {
        enumerateInputEventsHandlers.removeHandler(handler);
    }

    public void addEventEx1Handler(EventEx1Handler handler) {
        eventEx1Handlers.addHandler(handler);
    }

    public void removeEventEx1Handler(EventEx1Handler handler) {
        eventEx1Handlers.removeHandler(handler);
    }

    public void addEventFrameHandler(EventFrameHandler handler) {
        eventFrameHandlers.addHandler(handler);
    }

    public void removeEventFrameHandler(EventFrameHandler handler) {
        eventFrameHandlers.removeHandler(handler);
    }

    public void addEventMultiplayerServerStartedHandler(EventMultiplayerServerStartedHandler handler) {
        eventMultiplayerServerStartedHandlers.addHandler(handler);
    }

    public void removeEventMultiplayerServerStartedHandler(EventMultiplayerServerStartedHandler handler) {
        eventMultiplayerServerStartedHandlers.removeHandler(handler);
    }

    public void addEventMultiplayerClientStartedHandler(EventMultiplayerClientStartedHandler handler) {
        eventMultiplayerClientStartedHandlers.addHandler(handler);
    }

    public void removeEventMultiplayerClientStartedHandler(EventMultiplayerClientStartedHandler handler) {
        eventMultiplayerClientStartedHandlers.removeHandler(handler);
    }

    public void addEventMultiplayerSessionEndedHandler(EventMultiplayerSessionEndedHandler handler) {
        eventMultiplayerSessionEndedHandlers.addHandler(handler);
    }

    public void removeEventMultiplayerSessionEndedHandler(EventMultiplayerSessionEndedHandler handler) {
        eventMultiplayerSessionEndedHandlers.removeHandler(handler);
    }

    public void addEventObjectAddRemoveHandler(EventObjectAddRemoveHandler handler) {
        eventObjectAddRemoveHandlers.addHandler(handler);
    }

    public void removeEventObjectAddRemoveHandler(EventObjectAddRemoveHandler handler) {
        eventObjectAddRemoveHandlers.removeHandler(handler);
    }

    public void addFacilityDataHandler(FacilityDataHandler handler) {
        facilityDataHandlers.addHandler(handler);
    }

    public void removeFacilityDataHandler(FacilityDataHandler handler) {
        facilityDataHandlers.removeHandler(handler);
    }

    public void addFacilityDataEndHandler(FacilityDataEndHandler handler) {
        facilityDataEndHandlers.addHandler(handler);
    }

    public void removeFacilityDataEndHandler(FacilityDataEndHandler handler) {
        facilityDataEndHandlers.removeHandler(handler);
    }

    public void addJetwayDataHandler(JetwayDataHandler handler) {
        jetwayDataHandlers.addHandler(handler);
    }

    public void removeJetwayDataHandler(JetwayDataHandler handler) {
        jetwayDataHandlers.addHandler(handler);
    }

    public void addQuitHandler(QuitHandler handler) {
        quitHandlers.addHandler(handler);
    }

    public void removeQuitHandler(QuitHandler handler) {
        quitHandlers.removeHandler(handler);
    }

    public void addReservedKeyHandler(ReservedKeyHandler handler) {
        reservedKeyHandlers.addHandler(handler);
    }

    public void removeReservedKeyHandler(ReservedKeyHandler handler) {
        reservedKeyHandlers.removeHandler(handler);
    }

    public void addExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandlers.addHandler(exceptionHandler);
    }

    public void removeExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandlers.removeHandler(exceptionHandler);
    }

    public void addEventHandler(EventHandler eventHandler) {
        this.eventHandlers.addHandler(eventHandler);
    }

    public void removeEventHandler(EventHandler eventHandler) {
        this.eventHandlers.removeHandler(eventHandler);
    }

    public void addEventFilenameHandler(EventFilenameHandler eventFilenameHandler) {
        this.eventFilenameHandlers.addHandler(eventFilenameHandler);
    }

    public void removeEventFilenameHandler(EventFilenameHandler eventFilenameHandler) {
        this.eventFilenameHandlers.removeHandler(eventFilenameHandler);
    }

    public void addSystemStateHandler(SystemStateHandler systemStateHandler) {
        this.systemStateHandlers.addHandler(systemStateHandler);
    }

    public void removeSystemStateHandler(SystemStateHandler systemStateHandler) {
        this.systemStateHandlers.removeHandler(systemStateHandler);
    }

    public void addAirportListHandler(AirportListHandler airportListHandler) {
        this.airportListHandlers.addHandler(airportListHandler);
    }

    public void removeAirportListHandler(AirportListHandler airportListHandler) {
        this.airportListHandlers.removeHandler(airportListHandler);
    }

    public void addWaypointListHandler(WaypointListHandler waypointListHandler) {
        this.waypointListHandlers.addHandler(waypointListHandler);
    }

    public void removeWaypointListHandler(WaypointListHandler waypointListHandler) {
        this.waypointListHandlers.removeHandler(waypointListHandler);
    }

    public void addNdbListHandler(NdbListHandler ndbListHandler) {
        this.ndbListHandlers.addHandler(ndbListHandler);
    }

    public void removeNdbListHandler(NdbListHandler ndbListHandler) {
        this.ndbListHandlers.removeHandler(ndbListHandler);
    }

    public void addVorListHandler(VorListHandler vorListHandler) {
        this.vorListHandlers.addHandler(vorListHandler);
    }

    public void removeVorListHandler(VorListHandler vorListHandler) {
        this.vorListHandlers.removeHandler(vorListHandler);
    }

    public void addOpenHandler(OpenHandler openHandler) {
        this.openHandlers.addHandler(openHandler);
    }

    public void removeOpenHandler(OpenHandler openHandler) {
        this.openHandlers.removeHandler(openHandler);
    }

    public void addSimobjectDataHandler(SimobjectDataHandler simobjectDataHandler) {
        this.simobjectDataHandlers.addHandler(simobjectDataHandler);
    }

    public void removeSimobjectDataHandler(SimobjectDataHandler simobjectDataHandler) {
        this.simobjectDataHandlers.removeHandler(simobjectDataHandler);
    }

    public void addResponseHandler(ResponseHandler handler) {
        this.responseHandlers.addHandler(handler);
    }

    public void removeResponseHandler(ResponseHandler handler) {
        this.responseHandlers.removeHandler(handler);
    }

    public void addSimobjectDataByTypeHandler(SimobjectDataByTypeHandler simobjectDataByTypeHandler) {
        this.simobjectDataByTypeHandlers.addHandler(simobjectDataByTypeHandler);
    }

    public void removeSimobjectDataByTypeHandler(SimobjectDataByTypeHandler simobjectDataByTypeHandler) {
        this.simobjectDataByTypeHandlers.removeHandler(simobjectDataByTypeHandler);
    }

    private static class HandlerList<H, R> {

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

        public void removeHandler(H handler) {
            handlers.add(handler);
        }
    }
}