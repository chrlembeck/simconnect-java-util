package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * Superclass of all responses that will be received from the simulator over the simconnect interface.
 * Contains the data that is common to all kinds of response types as its size, the current version and its type id.
 */
public abstract class SimResponse {

    /**
     * The total size of the returned structure in bytes (that is, not usually the size of the SIMCONNECT_RECV structure, but of the structure that inherits it).
     */
    protected final int size;

    /**
     * The version number of the SimConnect server.
     */
    protected final int version;

    /**
     * The ID of the returned structure. One member of SIMCONNECT_RECV_ID.
     */
    protected final int typeId;

    /**
     * Creates a new response instance.
     *
     * @param size    The total size of the returned structure in bytes (that is, not usually the size of the SIMCONNECT_RECV structure, but of the structure that inherits it).
     * @param version The version number of the SimConnect server.
     * @param typeId  The ID of the returned structure. One member of SIMCONNECT_RECV_ID.
     */
    protected SimResponse(int size, int version, int typeId) {
        this.size = size;
        this.version = version;
        this.typeId = typeId;
    }

    SimResponse(ByteBuffer buffer) {
        this(buffer.getInt(), // size
                buffer.getInt(), // version
                buffer.getInt()); // typeId
    }

    /**
     * Returns the total size of the returned structure in bytes.
     *
     * @return The total size of the returned structure in bytes (that is, not usually the size of the SIMCONNECT_RECV structure, but of the structure that inherits it).
     */
    public int getSize() {
        return size;
    }

    /**
     * returns the version number of the SimConnect server.
     *
     * @return The version number of the SimConnect server.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Returns the ID of the returned structure.
     *
     * @return The ID of the returned structure. One member of SIMCONNECT_RECV_ID.
     */
    public int getTypeID() {
        return typeId;
    }

    /**
     * Reads the next response from the given byte buffer and transforms it into its specific SimResponse implementation.
     *
     * @param buffer The buffer from which to read the data from.
     * @return Object representation of the read simconnect response. If the type is new or not yet implemented, the result will be of type {@link UnknownResponse}.
     */
    public static SimResponse parseResponse(ByteBuffer buffer) {
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
}