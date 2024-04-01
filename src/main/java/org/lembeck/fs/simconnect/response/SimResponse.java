package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public abstract class SimResponse {

    protected final int size;

    protected final int version;

    protected final int typeId;

    public SimResponse(int size, int version, int typeId) {
        this.size = size;
        this.version = version;
        this.typeId = typeId;
    }

    SimResponse(ByteBuffer buffer) {
        this(buffer.getInt(), // size
                buffer.getInt(), // version
                buffer.getInt()); // typeId
    }

    public int getSize() {
        return size;
    }

    public int getVersion() {
        return version;
    }

    public int getTypeID() {
        return typeId;
    }

    public static SimResponse parseResponse(int size, ByteBuffer buffer) {
        int typeId = buffer.getInt(8);
        return switch (typeId) {
            case 0x01 -> new RecvExceptionResponse(buffer);
            case 0x02 -> new RecvOpenResponse(buffer);
            case 0x03 -> new RecvQuitResponse(buffer);
            case 0x04 -> new RecvEventResponse(buffer);
            case 0x05 -> new RecvEventObjectAddRemoveResponse(buffer);
            case 0x06 -> new RecvEventFilenameResponse(buffer);
            case 0x07 -> new RecvEventFrameResponse(buffer);
            case 0x08 -> new RecvSimobjectDataResponse(buffer);
            case 0x09 -> new RecvSimobjectDataByTypeResponse(buffer);
            // 0x0a: *deprecated* SIMCONNECT_RECV_WEATHER_OBSERVATION
            // 0x0b: *deprecated* SIMCONNECT_RECV_CLOUD_STATE
            case 0x0c -> new RecvAssignedObjectIdResponse(buffer);
            case 0x0d -> new RecvReservedKeyResponse(buffer);
            // 0x0e: *deprecated* SIMCONNECT_RECV_CUSTOM_ACTION
            case 0x0f -> new RecvSystemStateResponse(buffer);
            case 0x10 -> new RecvClientDataResponse(buffer);
            // 0x11: *deprecated* SIMCONNECT_RECV_EVENT_WEATHER_MODE
            case 0x12 -> new RecvAirportListResponse(buffer);
            case 0x13 -> new RecvVorListResponse(buffer);
            case 0x14 -> new RecvNdbListResponse(buffer);
            case 0x15 -> new RecvWaypointListResponse(buffer);
            case 0x16 -> new RecvEventMultiplayerServerStartedResponse(buffer);
            case 0x17 -> new RecvEventMultiplayerClientStartedResponse(buffer);
            case 0x18 -> new RecvEventMultiplayerSessionEndedResponse(buffer);
            // 0x19: SIMCONNECT_RECV_EVENT_RACE_END
            // 0x1a: SIMCONNECT_RECV_EVENT_RACE_LAP
            case 0x1b -> new RecvEventEx1Response(buffer);
            case 0x1c -> new RecvFacilityDataResponse(buffer);
            case 0x1d -> new RecvFacilityDataEndResponse(buffer);
            // 0x1e: SIMCONNECT_RECV_FACILITY_MINIMAL_LIST
            case 0x1f -> new RecvJetwayDataResponse(buffer);
            case 0x20 -> new RecvControllersListResponse(buffer);
            // 0x21: SIMCONNECT_RECV_ACTION_CALLBACK
            case 0x22 -> new RecvEnumerateInputEventsResponse(buffer);
            // 0x23: SIMCONNECT_RECV_GET_INPUT_EVENT
            // 0x24: SIMCONNECT_RECV_SUBSCRIBE_INPUT_EVENT
            // 0x25: SIMCONNECT_RECV_ENUMERATE_INPUT_EVENT_PARAMS

            default -> new UnknownResponse(buffer);
        };
    }

    public static String toString(byte[] data) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i] & 0xff);
            if (i < data.length-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}