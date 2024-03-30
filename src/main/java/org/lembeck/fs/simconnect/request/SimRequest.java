package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.SIMCONNECT_PROTOCOL_FS2020;

public abstract class SimRequest {

    private int size = -1;

    private final int version;

    private final int typeID;

    private int identifier;

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

    public SimRequest(int typeID) {
        this.typeID = typeID;
        this.version = SIMCONNECT_PROTOCOL_FS2020;
    }

    public int getSize() {
        return size;
    }

    public int getVersion() {
        return version;
    }

    public int getTypeID() {
        return typeID;
    }

    public int getIdentifier() {
        return identifier;
    }

    public static SimRequest parseRequest(int size, ByteBuffer buffer) {
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
            case 0xf0000043 -> new RequestFacilitesListRequest(buffer);
            case 0xf0000044 -> new TransmitClientEventEx1Request(buffer);
            case 0xf000004d -> new MapInputEventToClientEventEx1Request(buffer);

            case 0xf0000050 -> new GetInputEventRequest(buffer);
            case 0xf0000051 -> new SetInputEventRequest(buffer);
            default -> new UnknownRequest(buffer);
        };
    }

    public final void write(ByteBuffer outBuffer) {
        outBuffer.position(4);
        outBuffer.putInt(version);
        outBuffer.putInt(typeID);
        outBuffer.putInt(identifier);
        writeRequest(outBuffer);
        size = outBuffer.position();
        outBuffer.putInt(0, size);
    }

    protected abstract void writeRequest(ByteBuffer outBuffer);

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}