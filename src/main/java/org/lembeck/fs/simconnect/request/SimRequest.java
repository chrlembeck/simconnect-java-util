package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.SIMCONNECT_PROTOCOL_FS2020;

/**
 * Superclass of all requests that can be sent to the simulator using the simconnect interface.
 */
public abstract class SimRequest {

    /**
     * Size of the request in bytes.
     */
    protected int size = -1;

    /**
     * Used protocol version of the conversation.
     */
    protected final int version;

    /**
     * Type of the request.
     */
    protected final int typeID;

    /**
     * Unique identifier of the request.
     */
    protected int identifier;

    SimRequest(int size, int version, int typeID, int identifier) {
        this.size = size;
        this.version = version;
        this.typeID = typeID;
        this.identifier = identifier;
    }

    SimRequest(ByteBuffer buffer) {
        this(buffer.getInt(), // size
                buffer.getInt(), // version
                buffer.getInt(), // typeId
                buffer.getInt()); // identifier
    }

    /**
     * Creates a new SimRequest object of the given type.
     *
     * @param typeID Type of the request.
     */
    protected SimRequest(int typeID) {
        this.typeID = typeID;
        this.version = SIMCONNECT_PROTOCOL_FS2020;
    }

    /**
     * Returns the size of this request in bytes.
     *
     * @return Size of this request in bytes.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the protocol version of the conversation.
     *
     * @return Used protocol version of the conversation.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Returns the type id of this request.
     *
     * @return Type id of this request.
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * Returns the unique identifier of this request.
     *
     * @return Unique identifier of this request.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Reads the next request out of the given byte buffer and transforms it into a specific SimRequest object.
     *
     * @param buffer The buffer to read the bytes of the request from.
     * @return SimRequest object representing the type and content of the request.
     */
    public static SimRequest parseRequest(ByteBuffer buffer) {
        int typeId = buffer.getInt(8);
        return switch (typeId) {
            case 0xf0000001 -> new HelloRequest(buffer);
            case 0xf0000004 -> new MapClientEventToSimEventRequest(buffer);
            case 0xf0000005 -> new TransmitClientEventRequest(buffer);
            case 0xf0000006 -> new SetSystemEventStateRequest(buffer);
            case 0xf0000007 -> new AddClientEventToNotificationGroupRequest(buffer);
            case 0xf0000008 -> new RemoveClientEventRequest(buffer);
            case 0xf0000009 -> new SetNotificationGroupPriorityRequest(buffer);
            case 0xf000000a -> new ClearNotificationGroupRequest(buffer);
            case 0xf000000b -> new RequestNotificationGroupRequest(buffer);
            case 0xf000000c -> new AddToDataDefinitionRequest(buffer);
            case 0xf000000d -> new ClearDataDefinitionRequest(buffer);
            case 0xf000000e -> new RequestDataOnSimObjectRequest(buffer);
            case 0xf000000f -> new RequestDataOnSimObjectTypeRequest(buffer);
            case 0xf0000010 -> new SetDataOnSimObjectRequest(buffer);
            case 0xf0000012 -> new SetInputGroupPriorityRequest(buffer);
            case 0xf0000013 -> new RemoveInputEventRequest(buffer);
            case 0xf0000014 -> new ClearInputGroupRequest(buffer);
            case 0xf0000015 -> new SetInputGroupStateRequest(buffer);
            case 0xf0000016 -> new RequestReservedKeyRequest(buffer);
            case 0xf0000017 -> new SubscribeToSystemEventRequest(buffer);
            case 0xf0000018 -> new UnsubscribeFromSystemEventRequest(buffer);
            //19-26 are deprecated
            case 0x00000027 -> new AICreateParkedATCAircraftRequest(buffer);
            case 0x00000028 -> new AICreateEnrouteATCAircraftRequest(buffer);
            case 0x00000029 -> new AICreateNonATCAircraftRequest(buffer);
            case 0x0000002a -> new AICreateSimulatedObjectRequest(buffer);
            case 0x0000002b -> new AIReleaseControlRequest(buffer);
            case 0x0000002c -> new AIRemoveObjectRequest(buffer);
            case 0x0000002d -> new AISetAircraftFlightPlanRequest(buffer);
            case 0xf0000030 -> new CameraSetRelative6DofRequest(buffer);
            case 0xf0000035 -> new RequestSystemStateRequest(buffer);
            case 0xf0000036 -> new SetSystemStateRequest(buffer);
            case 0xf0000037 -> new MapClientDataNameToIDRequest(buffer);
            case 0xf0000038 -> new CreateClientDataRequest(buffer);
            case 0xf0000039 -> new AddToClientDataDefinitionRequest(buffer);
            case 0xf000003a -> new ClearClientDataDefinitionRequest(buffer);
            case 0xf000003b -> new RequestClientDataRequest(buffer);
            case 0xf000003c -> new SetClientDataDefinitionRequest(buffer);
            case 0xf000003d -> new FlightLoadRequest(buffer);
            case 0xf000003e -> new FlightSaveRequest(buffer);
            case 0xf000003f -> new FlightPlanLoadRequest(buffer);
            // 0x40 is deprecated
            case 0xf0000041 -> new SubscribeToFacilitiesRequest(buffer);
            case 0xf0000042 -> new UnsubscribeToFacilitiesRequest(buffer);
            case 0xf0000043 -> new RequestFacilitiesListRequest(buffer);
            case 0xf0000044 -> new TransmitClientEventEx1Request(buffer);
            case 0xf0000045 -> new AddToFacilityDefinitionRequest(buffer);
            case 0xf0000046 -> new RequestFacilityDataRequest(buffer);
            case 0xf0000047 -> new SubscribeToFacilitiesEx1Request(buffer);
            case 0xf0000048 -> new UnsubscribeToFacilitiesEx1Request(buffer);
            case 0xf0000049 -> new RequestFacilitiesListEx1Request(buffer);
            case 0xf000004a -> new RequestFacilityDataEx1Request(buffer);
            case 0xf000004b -> new RequestJetwayDataRequest(buffer);
            case 0xf000004c -> new EnumerateControllersRequest(buffer);
            case 0xf000004d -> new MapInputEventToClientEventEx1Request(buffer);

            case 0xf000004f -> new EnumerateInputEventsRequest(buffer);
            case 0xf0000050 -> new GetInputEventRequest(buffer);
            case 0xf0000051 -> new SetInputEventRequest(buffer);
            case 0xf0000052 -> new SubscribeInputEventRequest(buffer);
            case 0xf0000053 -> new UnsubscribeInputEventRequest(buffer);
            case 0xf0000054 -> new EnumerateInputEventParamsRequest(buffer);
            case 0xf0000055 -> new AddFacilityDataDefinitionFilterRequest(buffer);
            case 0xf0000056 -> new ClearAllFacilityDataDefinitionFiltersRequest(buffer);
            default -> new UnknownRequest(buffer);
        };
    }

    /**
     * Transforms the request into its byte sequence representation and writes it to the outgoing ByteBuffer.
     * Subclasses of this class have to implement the {@link SimRequest#writeRequest(ByteBuffer)} method to send their
     * specific data.
     *
     * @param outBuffer The buffer, the bytes are written into. The buffer will be used to send the data to the
     *                  simconnect socket stream.
     * @see SimRequest#writeRequest(ByteBuffer)
     */
    public final void write(ByteBuffer outBuffer) {
        outBuffer.position(4);
        outBuffer.putInt(version);
        outBuffer.putInt(typeID);
        outBuffer.putInt(identifier);
        writeRequest(outBuffer);
        size = outBuffer.position();
        outBuffer.putInt(0, size);
    }

    /**
     * Transforms the content of the request into its byte sequence representation and writes it to the outgoing ByteBuffer.
     * Implementors of this method do not have to worry about the four components size, protocol version,
     * request type id and request identifier. These four information are handled by the superclass SimRequest.
     *
     * @param outBuffer The buffer, the bytes are written into. The buffer will be used to send the data to the
     *                  simconnect socket stream.
     * @see SimRequest#write(ByteBuffer)
     */
    protected abstract void writeRequest(ByteBuffer outBuffer);

    /**
     * Updates the unique identifier of this request. The method will be called, when the message is sent to the
     * simulator.
     *
     * @param identifier The new unique identifier.
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}