package org.lembeck.fs.simconnect;


import flightsim.simconnect.ClientDataPeriod;
import org.lembeck.fs.simconnect.request.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class MySimConnect {

    private SocketChannel channel;

    private final ResponseReceiver responseReceiver;

    private final ByteBuffer inBuffer;

    private final ByteBuffer outBuffer;

    private final AtomicInteger lastRequestIdentifier = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        MySimConnect mySimConnect = new MySimConnect();
        mySimConnect.connect("192.168.0.170", 26011, "MySimConnect");
    }

    public MySimConnect() {
        responseReceiver = new ResponseReceiver();
        this.inBuffer = ByteBuffer.allocateDirect(64 * 1024);
        this.outBuffer = ByteBuffer.allocateDirect(64 * 1024);
        inBuffer.order(LITTLE_ENDIAN);
        outBuffer.order(LITTLE_ENDIAN);
    }

    public void connect(String hostname, int port, String appName) throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(hostname, port));
        responseReceiver.setChannel(channel);
        Thread thread = new Thread(responseReceiver);
        thread.setDaemon(false);
        thread.setName("Simconnect Request Receiver");
        thread.start();
        write(new HelloRequest(appName));
    }

    public void close() {
        try {
            channel.close();
        } catch (IOException ioe) {
            // ignore
        }
    }

    public ResponseReceiver getRequestReceiver() {
        return responseReceiver;
    }

    public <T extends SimRequest> T write(T request) throws IOException {
        request.setIdentifier(lastRequestIdentifier.incrementAndGet());
        synchronized (outBuffer) {
            outBuffer.clear();
            request.write(outBuffer);
            outBuffer.flip();
            channel.write(outBuffer);
        }
        return request;
    }

    public MapClientEventToSimEventRequest mapClientEventToSimEvent(int eventID, String eventName) throws IOException {
        return write(new MapClientEventToSimEventRequest(eventID, eventName));
    }

    public TransmitClientEventRequest transmitClientEvent(int objectID, int clientEventID, int data, int notificationGroupID, int eventFlag) throws IOException {
        return write(new TransmitClientEventRequest(objectID, clientEventID, data, notificationGroupID, eventFlag));
    }

    public SetSystemEventStateRequest setSystemEventState(int clientEventID, State state) throws IOException {
        return write(new SetSystemEventStateRequest(clientEventID, state));
    }

    public AddClientEventToNotificationGroupRequest addClientEventToNotificationGroup(int notificationGroupID, int clientEventID, boolean maskable) throws IOException {
        return write(new AddClientEventToNotificationGroupRequest(notificationGroupID, clientEventID, maskable));
    }

    public RemoveClientEventRequest removeClientEvent(int notificationGroupID, int clientEventID) throws IOException {
        return write(new RemoveClientEventRequest(notificationGroupID, clientEventID));
    }

    public SetNotificationGroupPriorityRequest setNotificationGroupPriority(int notificationGroupID, int priority) throws IOException {
        return write(new SetNotificationGroupPriorityRequest(notificationGroupID, priority));
    }

    public ClearNotificationGroupRequest clearNotificationGroup(int notificationGroupID) throws IOException {
        return write(new ClearNotificationGroupRequest(notificationGroupID));
    }

    public RequestNotificationGroupRequest requestNotificationGroup(int notificationGroupID, int reserved, int flags) throws IOException {
        return write(new RequestNotificationGroupRequest(notificationGroupID, reserved, flags));
    }

    public AddToDataDefinitionRequest addToDataDefinition(int defineID, String datumName, String unitsName, int datumType, float epsilon, int datumID) throws IOException {
        return write(new AddToDataDefinitionRequest(defineID, datumName, unitsName, datumType, epsilon, datumID));
    }

    public ClearDataDefinitionRequest clearDataDefinition(int dataDefinitionID) throws IOException {
        return write(new ClearDataDefinitionRequest(dataDefinitionID));
    }

    public RequestDataOnSimObjectRequest requestDataOnSimObject(int dataRequestID, int dataDefinitionID, int objectID, SimconnectPeriod period, int dataRequestFlags, int origin, int interval, int limit) throws IOException {
        return write(new RequestDataOnSimObjectRequest(dataRequestID, dataDefinitionID, objectID, period, dataRequestFlags, origin, interval, limit));
    }

    public RequestDataOnSimObjectTypeRequest requestDataOnSimObjectType(int requestID, int defineID, int radiusMeters, SimObjectType type) throws IOException {
        return write(new RequestDataOnSimObjectTypeRequest(requestID, defineID, radiusMeters, type));
    }

    public SetDataOnSimObjectRequest setDataOnSimObject(int dataDefinitionID, int objectID, DataSetFlag dataSetFlag, int arrayCount, int unitSize, byte[] data) throws IOException {
        return write(new SetDataOnSimObjectRequest(dataDefinitionID, objectID, dataSetFlag, arrayCount, unitSize, data));
    }

    public SetInputGroupPriorityRequest setInputGroupPriority(int groupID, int priority) throws IOException {
        return write(new SetInputGroupPriorityRequest(groupID, priority));
    }

    public RemoveInputEventRequest removeInputEvent(int groupID, String inputDefinition) throws IOException {
        return write(new RemoveInputEventRequest(groupID, inputDefinition));
    }

    public ClearInputGroupRequest clearInputGroup(int groupID) throws IOException {
        return write(new ClearInputGroupRequest(groupID));
    }

    public SetInputGroupStateRequest SetInputGroupState(int groupID, int state) throws IOException {
        return write(new SetInputGroupStateRequest(groupID, state));
    }

    public RequestReservedKeyRequest requestReservedKey(int eventID, String keyChoice1, String keyChoice2, String keyChoice3) throws IOException {
        return write(new RequestReservedKeyRequest(eventID, keyChoice1, keyChoice2, keyChoice3));
    }

    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID, SystemEventName eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID, String eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    public UnsubscribeFromSystemEventRequest unsubscribeFromSystemEvent(int eventID) throws IOException {
        return write(new UnsubscribeFromSystemEventRequest(eventID));
    }

    public AICreateParkedATCAircraftRequest aiCreateParkedATCAircraft(String containerTitle, String tailNumber, String airportIcaoID, int requestID) throws IOException {
        return write(new AICreateParkedATCAircraftRequest(containerTitle, tailNumber, airportIcaoID, requestID));
    }

    public AICreateEnrouteATCAircraftRequest aiCreateEnrouteATCAircraft(String containerTitle, String tailNumber, int flightNumber, String flightPlanPath, float flightPlanPosition, boolean touchAndGo, int requestID) throws IOException {
        return write(new AICreateEnrouteATCAircraftRequest(containerTitle, tailNumber, flightNumber, flightPlanPath, flightPlanPosition, touchAndGo, requestID));
    }

    public AICreateNonATCAircraftRequest aiCreateNonATCAircraft(String containerTitle, String tailNumber, InitPosition initPosition, int requestID) throws IOException {
        return write(new AICreateNonATCAircraftRequest(containerTitle, tailNumber, initPosition, requestID));
    }

    public AICreateSimulatedObjectRequest aiCreateSimulatedObject(String containerTitle, InitPosition initPosition, int requestID) throws IOException {
        return write(new AICreateSimulatedObjectRequest(containerTitle, initPosition, requestID));
    }

    public AIReleaseControlRequest aiReleaseControl(int objectID, int requestID) throws IOException {
        return write(new AIReleaseControlRequest(objectID, requestID));
    }

    public AIRemoveObjectRequest aiRemoveObject(int objectID, int requestID) throws IOException {
        return write(new AIRemoveObjectRequest(objectID, requestID));
    }

    public AISetAircraftFlightPlanRequest aiSetAircraftFlightPlan(int objectID, String flightPlanPath, int requestID) throws IOException {
        return write(new AISetAircraftFlightPlanRequest(objectID, flightPlanPath, requestID));
    }

    public CameraSetRelative6DofRequest cameraSetRelative6DOF(float deltaX, float deltaY, float deltaZ, float pitchDeg, float bankDeg, float headingDeg) throws IOException {
        return write(new CameraSetRelative6DofRequest(deltaX, deltaY, deltaZ, pitchDeg, bankDeg, headingDeg));
    }

    public RequestSystemStateRequest requestSystemState(int requestID, String state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    public SetSystemStateRequest setSystemState(String state, int intParam, float floatParam, String stringParam) throws IOException {
        return write(new SetSystemStateRequest(state, intParam, floatParam, stringParam));
    }

    public MapClientDataNameToIDRequest mapClientDataNameToID(String clientDataName, int clientDataID) throws IOException {
        return write(new MapClientDataNameToIDRequest(clientDataName, clientDataID));
    }

    public CreateClientDataRequest createClientDataRequest(int clientDataID, int dataSize, boolean readonly) throws IOException {
        return write(new CreateClientDataRequest(clientDataID, dataSize, readonly));
    }

    public AddToClientDataDefinitionRequest addToClientDataDefinitionRequest(int defineID, int offset, int sizeOrType, float epsilon, int datumID) throws IOException {
        return write(new AddToClientDataDefinitionRequest(defineID, offset, sizeOrType, epsilon, datumID));
    }

    public ClearClientDataDefinitionRequest clearClientDataDefinition(int defineID) throws IOException {
        return write(new ClearClientDataDefinitionRequest(defineID));
    }

    public RequestClientDataRequest requestClientData(int clientDataID, int requestID, int defineID, ClientDataPeriod period, int flags, int origin, int interval, int limit) throws IOException {
        return write(new RequestClientDataRequest(clientDataID, requestID, defineID, period, flags, origin, interval, limit));
    }

    public SetClientDataDefinitionRequest setClientDataDefinition(int clientDataID, int defineID, boolean tagged, int reserved, int dataSize, byte[] data) throws IOException {
        return write(new SetClientDataDefinitionRequest(clientDataID, defineID, tagged, reserved, dataSize, data));
    }

    public FlightLoadRequest flightLoad(String filename) throws IOException {
        return write(new FlightLoadRequest(filename));
    }

    public FlightSaveRequest flightSave(String filename, String title, String description, int flags) throws IOException {
        return write(new FlightSaveRequest(filename, title, description, flags));
    }

    public FlightPlanLoadRequest flightPlanLoad(String filename) throws IOException {
        return write(new FlightPlanLoadRequest(filename));
    }

    public RequestSystemStateRequest requestSystemState(int requestID, SystemState state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    public SubscribeToFacilitiesRequest subscribeToFacilities(FacilityListType facilityListType, int requestId) throws IOException {
        return write(new SubscribeToFacilitiesRequest(facilityListType, requestId));
    }

    public UnsubscribeToFacilitiesRequest unsubscribeToFacilities(FacilityListType facilityListType) throws IOException {
        return write(new UnsubscribeToFacilitiesRequest(facilityListType));
    }

    public RequestFacilitesListRequest requestFacilitiesList(FacilityListType facilityListType, int requestId) throws IOException {
        return write(new RequestFacilitesListRequest(facilityListType, requestId));
    }

    public TransmitClientEventEx1Request transmitClientEventEx1Request(int objectID, int eventID, int notificationGroupID, int eventFlag, int data0, int data1, int data2, int data3, int data4) throws IOException {
        return write(new TransmitClientEventEx1Request(objectID, eventID, notificationGroupID, eventFlag, data0, data1, data2, data3, data4));
    }

    public MapInputEventToClientEventEx1Request mapInputEventToClientEventEx1(int groupID, String inputDefinition, int downEventID, int downValue, int upEventID, int upValue, boolean bMaskable) throws IOException {
        return write(new MapInputEventToClientEventEx1Request(groupID, inputDefinition, downEventID, downValue, upEventID, upValue, bMaskable));
    }

    public GetInputEventRequest getInputEventRequest(int requestID, long hash) throws IOException {
        return write(new GetInputEventRequest(requestID, hash));
    }

    public SetInputEventRequest setInputEvent(long hash, float floatValue) throws IOException {
        return write(new SetInputEventRequest(hash, floatValue));
    }

    public SetInputEventRequest setInputEvent(long hash, String stringValue) throws IOException {
        return write(new SetInputEventRequest(hash, stringValue));
    }
}