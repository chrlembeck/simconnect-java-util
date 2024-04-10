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

/**
 * The central class that handles all incoming responses from the simulators simconnect interface. Here is the place,
 * client objects can be registered as listeners for one or more incoming response types.
 *
 * @see SimResponse
 */
public class ResponseReceiver implements Runnable {

    private SocketChannel channel;

    private boolean running;

    ResponseReceiver() {
    }

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
    private final HandlerList<UnknownResponseHandler, UnknownResponse> unkownResponseHandlers = new HandlerList<>((handler, response) -> handler.handleUnknonwResponse(response));

    /**
     * As long as the simconnector interface is in running state, this method reads data from the incoming socket stream
     * and transforms it into the relating response objects. The read responses are then propagated to the specific
     * response handlers.
     */
    @Override
    public void run() {
        running = true;
        ByteBuffer readBuffer = ByteBuffer.allocate(64 * 1024);
        readBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int bytesRead = 0;
        try {
            while (running) {
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

    /**
     * Tells this class to stop listening to further incoming data from the simulators simconnect interface.
     */
    public void stop() {
        running = false;
    }

    void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

    private void handleResponse(SimResponse response) {
        responseHandlers.notifyHandlers(response);
        switch (response) {
            case UnknownResponse r:
                unkownResponseHandlers.notifyHandlers(r);
                break;
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

    /**
     * Adds a new listener for RecvAssignedObjectIdResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvAssignedObjectIdResponse responses.
     * @see RecvAssignedObjectIdResponse
     */
    public void addAssignedObjectIdHandler(AssignedObjectIdHandler handler) {
        assignedObjectIdHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvAssignedObjectIdResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeAssignedObjectIdHandler(AssignedObjectIdHandler handler) {
        assignedObjectIdHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvClientDataResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvClientDataResponse responses.
     * @see RecvClientDataResponse
     */
    public void addClientDataHandler(ClientDataHandler handler) {
        clientDataHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvClientDataResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeClientDataHandler(ClientDataHandler handler) {
        clientDataHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvControllersListResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvControllersListResponse responses.
     * @see RecvControllersListResponse
     */
    public void addControllersListHandler(ControllersListHandler handler) {
        controllersListHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvControllersListResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeControllersListHandler(ControllersListHandler handler) {
        controllersListHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEnumerateInputEventsResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEnumerateInputEventsResponse responses.
     * @see RecvEnumerateInputEventsResponse
     */
    public void addEnumerateInputEventsHandler(EnumerateInputEventsHandler handler) {
        enumerateInputEventsHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEnumerateInputEventsResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEnumerateInputEventsHandler(EnumerateInputEventsHandler handler) {
        enumerateInputEventsHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventEx1Response responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventEx1Response responses.
     * @see RecvEventEx1Response
     */
    public void addEventEx1Handler(EventEx1Handler handler) {
        eventEx1Handlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventEx1Response response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventEx1Handler(EventEx1Handler handler) {
        eventEx1Handlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventFrameResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventFrameResponse responses.
     * @see RecvEventFrameResponse
     */
    public void addEventFrameHandler(EventFrameHandler handler) {
        eventFrameHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventFrameResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventFrameHandler(EventFrameHandler handler) {
        eventFrameHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventMultiplayerServerStartedResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventMultiplayerServerStartedResponse responses.
     * @see RecvEventMultiplayerServerStartedResponse
     */
    public void addEventMultiplayerServerStartedHandler(EventMultiplayerServerStartedHandler handler) {
        eventMultiplayerServerStartedHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventMultiplayerServerStartedResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventMultiplayerServerStartedHandler(EventMultiplayerServerStartedHandler handler) {
        eventMultiplayerServerStartedHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventMultiplayerClientStartedResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventMultiplayerClientStartedResponse responses.
     * @see RecvEventMultiplayerClientStartedResponse
     */
    public void addEventMultiplayerClientStartedHandler(EventMultiplayerClientStartedHandler handler) {
        eventMultiplayerClientStartedHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventMultiplayerClientStartedResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventMultiplayerClientStartedHandler(EventMultiplayerClientStartedHandler handler) {
        eventMultiplayerClientStartedHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventMultiplayerSessionEndedResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventMultiplayerSessionEndedResponse responses.
     * @see RecvEventMultiplayerSessionEndedResponse
     */
    public void addEventMultiplayerSessionEndedHandler(EventMultiplayerSessionEndedHandler handler) {
        eventMultiplayerSessionEndedHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventMultiplayerSessionEndedResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventMultiplayerSessionEndedHandler(EventMultiplayerSessionEndedHandler handler) {
        eventMultiplayerSessionEndedHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvEventObjectAddRemoveResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvEventObjectAddRemoveResponse responses.
     * @see RecvEventObjectAddRemoveResponse
     */
    public void addEventObjectAddRemoveHandler(EventObjectAddRemoveHandler handler) {
        eventObjectAddRemoveHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvEventObjectAddRemoveResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventObjectAddRemoveHandler(EventObjectAddRemoveHandler handler) {
        eventObjectAddRemoveHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvFacilityDataResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvFacilityDataResponse responses.
     * @see RecvFacilityDataResponse
     */
    public void addFacilityDataHandler(FacilityDataHandler handler) {
        facilityDataHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvFacilityDataResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeFacilityDataHandler(FacilityDataHandler handler) {
        facilityDataHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvFacilityDataEndResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvFacilityDataEndResponse responses.
     * @see RecvFacilityDataEndResponse
     */
    public void addFacilityDataEndHandler(FacilityDataEndHandler handler) {
        facilityDataEndHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvFacilityDataEndResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeFacilityDataEndHandler(FacilityDataEndHandler handler) {
        facilityDataEndHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvJetwayDataResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvJetwayDataResponse responses.
     * @see RecvJetwayDataResponse
     */
    public void addJetwayDataHandler(JetwayDataHandler handler) {
        jetwayDataHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvJetwayDataResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeJetwayDataHandler(JetwayDataHandler handler) {
        jetwayDataHandlers.addHandler(handler);
    }

    /**
     * Adds a new listener for RecvQuitResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvQuitResponse responses.
     * @see RecvQuitResponse
     */
    public void addQuitHandler(QuitHandler handler) {
        quitHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvQuitResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeQuitHandler(QuitHandler handler) {
        quitHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvReservedKeyResponse responses.
     *
     * @param handler The new listener, that should be informed about incoming RecvReservedKeyResponse responses.
     * @see RecvReservedKeyResponse
     */
    public void addReservedKeyHandler(ReservedKeyHandler handler) {
        reservedKeyHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of RecvReservedKeyResponse response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeReservedKeyHandler(ReservedKeyHandler handler) {
        reservedKeyHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvExceptionResponse responses.
     *
     * @param exceptionHandler The new listener, that should be informed about incoming RecvExceptionResponse responses.
     * @see RecvExceptionResponse
     */
    public void addExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandlers.addHandler(exceptionHandler);
    }

    /**
     * Removes the passed listener from the list of RecvExceptionResponse response handlers.
     *
     * @param exceptionHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandlers.removeHandler(exceptionHandler);
    }

    /**
     * Adds a new listener for RecvEventResponse responses.
     *
     * @param eventHandler The new listener, that should be informed about incoming RecvEventResponse responses.
     * @see RecvEventResponse
     */
    public void addEventHandler(EventHandler eventHandler) {
        this.eventHandlers.addHandler(eventHandler);
    }

    /**
     * Removes the passed listener from the list of RecvEventResponse response handlers.
     *
     * @param eventHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventHandler(EventHandler eventHandler) {
        this.eventHandlers.removeHandler(eventHandler);
    }

    /**
     * Adds a new listener for RecvEventFilenameResponse responses.
     *
     * @param eventFilenameHandler The new listener, that should be informed about incoming RecvEventFilenameResponse responses.
     * @see RecvEventFilenameResponse
     */
    public void addEventFilenameHandler(EventFilenameHandler eventFilenameHandler) {
        this.eventFilenameHandlers.addHandler(eventFilenameHandler);
    }

    /**
     * Removes the passed listener from the list of RecvEventFilenameResponse response handlers.
     *
     * @param eventFilenameHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeEventFilenameHandler(EventFilenameHandler eventFilenameHandler) {
        this.eventFilenameHandlers.removeHandler(eventFilenameHandler);
    }

    /**
     * Adds a new listener for RecvSystemStateResponse responses.
     *
     * @param systemStateHandler The new listener, that should be informed about incoming RecvSystemStateResponse responses.
     * @see RecvSystemStateResponse
     */
    public void addSystemStateHandler(SystemStateHandler systemStateHandler) {
        this.systemStateHandlers.addHandler(systemStateHandler);
    }

    /**
     * Removes the passed listener from the list of RecvSystemStateResponse response handlers.
     *
     * @param systemStateHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeSystemStateHandler(SystemStateHandler systemStateHandler) {
        this.systemStateHandlers.removeHandler(systemStateHandler);
    }

    /**
     * Adds a new listener for RecvAirportListResponse responses.
     *
     * @param airportListHandler The new listener, that should be informed about incoming RecvAirportListResponse responses.
     * @see RecvAirportListResponse
     */
    public void addAirportListHandler(AirportListHandler airportListHandler) {
        this.airportListHandlers.addHandler(airportListHandler);
    }

    /**
     * Removes the passed listener from the list of RecvAirportListResponse response handlers.
     *
     * @param airportListHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeAirportListHandler(AirportListHandler airportListHandler) {
        this.airportListHandlers.removeHandler(airportListHandler);
    }

    /**
     * Adds a new listener for RecvWaypointListResponse responses.
     *
     * @param waypointListHandler The new listener, that should be informed about incoming RecvWaypointListResponse responses.
     * @see RecvWaypointListResponse
     */
    public void addWaypointListHandler(WaypointListHandler waypointListHandler) {
        this.waypointListHandlers.addHandler(waypointListHandler);
    }

    /**
     * Removes the passed listener from the list of RecvWaypointListResponse response handlers.
     *
     * @param waypointListHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeWaypointListHandler(WaypointListHandler waypointListHandler) {
        this.waypointListHandlers.removeHandler(waypointListHandler);
    }

    /**
     * Adds a new listener for RecvNdbListResponse responses.
     *
     * @param ndbListHandler The new listener, that should be informed about incoming RecvNdbListResponse responses.
     * @see RecvNdbListResponse
     */
    public void addNdbListHandler(NdbListHandler ndbListHandler) {
        this.ndbListHandlers.addHandler(ndbListHandler);
    }

    /**
     * Removes the passed listener from the list of RecvNdbListResponse response handlers.
     *
     * @param ndbListHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeNdbListHandler(NdbListHandler ndbListHandler) {
        this.ndbListHandlers.removeHandler(ndbListHandler);
    }

    /**
     * Adds a new listener for RecvVorListResponse responses.
     *
     * @param vorListHandler The new listener, that should be informed about incoming RecvVorListResponse responses.
     * @see RecvVorListResponse
     */
    public void addVorListHandler(VorListHandler vorListHandler) {
        this.vorListHandlers.addHandler(vorListHandler);
    }

    /**
     * Removes the passed listener from the list of RecvVorListResponse response handlers.
     *
     * @param vorListHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeVorListHandler(VorListHandler vorListHandler) {
        this.vorListHandlers.removeHandler(vorListHandler);
    }

    /**
     * Adds a new listener for RecvOpenResponse responses.
     *
     * @param openHandler The new listener, that should be informed about incoming RecvOpenResponse responses.
     * @see RecvOpenResponse
     */
    public void addOpenHandler(OpenHandler openHandler) {
        this.openHandlers.addHandler(openHandler);
    }

    /**
     * Removes the passed listener from the list of RecvOpenResponse response handlers.
     *
     * @param openHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeOpenHandler(OpenHandler openHandler) {
        this.openHandlers.removeHandler(openHandler);
    }

    /**
     * Adds a new listener for RecvSimobjectDataResponse responses.
     *
     * @param simobjectDataHandler The new listener, that should be informed about incoming RecvSimobjectDataResponse responses.
     * @see RecvSimobjectDataResponse
     */
    public void addSimobjectDataHandler(SimobjectDataHandler simobjectDataHandler) {
        this.simobjectDataHandlers.addHandler(simobjectDataHandler);
    }

    /**
     * Removes the passed listener from the list of RecvSimobjectDataResponse response handlers.
     *
     * @param simobjectDataHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeSimobjectDataHandler(SimobjectDataHandler simobjectDataHandler) {
        this.simobjectDataHandlers.removeHandler(simobjectDataHandler);
    }

    /**
     * Adds a new listener for all kinds of incoming responses. Listeners registeres here will be informed about every
     * response, this client receives from the simulators simconnect interface.
     *
     * @param handler The new listener, that should be informed about incoming responses.
     * @see SimResponse
     */
    public void addResponseHandler(ResponseHandler handler) {
        this.responseHandlers.addHandler(handler);
    }

    /**
     * Removes the passed listener from the list of general response handlers.
     *
     * @param handler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeResponseHandler(ResponseHandler handler) {
        this.responseHandlers.removeHandler(handler);
    }

    /**
     * Adds a new listener for RecvSimobjectDataByTypeResponse responses.
     *
     * @param simobjectDataByTypeHandler The new listener, that should be informed about incoming RecvSimobjectDataByTypeResponse responses.
     * @see RecvSimobjectDataByTypeResponse
     */
    public void addSimobjectDataByTypeHandler(SimobjectDataByTypeHandler simobjectDataByTypeHandler) {
        this.simobjectDataByTypeHandlers.addHandler(simobjectDataByTypeHandler);
    }

    /**
     * Removes the passed listener from the list of RecvSimobjectDataByTypeResponse response handlers.
     *
     * @param simobjectDataByTypeHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeSimobjectDataByTypeHandler(SimobjectDataByTypeHandler simobjectDataByTypeHandler) {
        this.simobjectDataByTypeHandlers.removeHandler(simobjectDataByTypeHandler);
    }

    /**
     * Adds a new listener for Unknown responses. These are the responses that are new to this interface and are not
     * yet implemented here.
     *
     * @param unknownResponseHandler The new listener, that should be informed about incoming UnknownResponse responses.
     * @see UnknownResponse
     */
    public void addUnknownResponseHandler(UnknownResponseHandler unknownResponseHandler) {
        this.unkownResponseHandlers.addHandler(unknownResponseHandler);
    }

    /**
     * Removes the passed listener from the list of unknown response handlers.
     *
     * @param unknownResponseHandler The listener to be removed. The listener will no longer be informed about further incoming responses.
     */
    public void removeUnknownResponseHandler(UnknownResponseHandler unknownResponseHandler) {
        this.unkownResponseHandlers.removeHandler(unknownResponseHandler);
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