package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestFacilityData_EX1 function is used to request data according to a predefined object, an ICAO
 * and a region. This function is practically identical in functionality to the SimConnect_RequestFacilityData
 * function, only it has an additional return value used to identify waypoints when there is an ICAO/Region overlap
 * with VOR or NDB.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData_EX1.htm</a>
 */
public class RequestFacilityDataEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004a;

    /**
     * Enumeration of the additional identifier to help differentiating overlapping icao identifiers.
     */
    public enum FacilityType {

        /**
         * Specifies the VOR facility type.
         */
        VOR,

        /**
         * Specifies the NDB facility type.
         */
        NDB,

        /**
         * Specifies the waypoint facility type.
         */
        WAYPOINT;

        /**
         * Returns the byte representation of this facility type.
         *
         * @return Byte representation of this facility type.
         */
        public byte toByte() {
            return switch (this) {
                case NDB -> 78;
                case VOR -> 86;
                case WAYPOINT -> 87;
            };
        }
    }

    private final int defineID;

    private final int requestID;

    private final String icao;

    private final String region;

    private final FacilityType type;

    RequestFacilityDataEx1Request(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        requestID = buffer.getInt();
        icao = SimUtil.readString(buffer, 16);
        region = SimUtil.readString(buffer, 4);
        type = switch (buffer.get()) {
            case 78 -> FacilityType.NDB;
            case 86 -> FacilityType.VOR;
            case 87 -> FacilityType.WAYPOINT;
            default -> null;
        };

    }

    /**
     * The SimConnect_RequestFacilityData_EX1 function is used to request data according to a predefined object, an ICAO
     * and a region. This function is practically identical in functionality to the SimConnect_RequestFacilityData
     * function, only it has an additional return value used to identify waypoints when there is an ICAO/Region overlap
     * with VOR or NDB.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param requestID The client defined request ID.
     * @param icao      Used to identify an airport, a VOR, an NDB or a waypoint.
     * @param region    Additional identifier for an airport, a VOR, an NDB or a waypoint. For airports, this can be
     *                  omitted without issue, however for VOR / NDB / Waypoints this should be supplied if possible,
     *                  although there are workarounds provided if it's not (see remarks, below).
     * @param type      Additional identifier for when requesting data to differentiate between waypoint/VOR/NDB when
     *                  there are overlapping ICAO/Region identifiers. Should be null for other facility types like
     *                  airports.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData_EX1.htm</a>
     */
    public RequestFacilityDataEx1Request(int defineID, int requestID, String icao, String region, FacilityType type) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.requestID = requestID;
        this.icao = icao;
        this.region = region;
        this.type = type;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(requestID);
        SimUtil.writeString(outBuffer, icao, 16);
        SimUtil.writeString(outBuffer, region, 4);
        outBuffer.put(type == null ? 0 : type.toByte());
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the client defined request ID.
     *
     * @return The client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the icao code to identify an airport, a VOR, an NDB or a waypoint.
     *
     * @return The icao code to identify an airport, a VOR, an NDB or a waypoint.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Returns an additional identifier for an airport, a VOR, an NDB or a waypoint. For airports, this can be omitted
     * without issue, however for VOR / NDB / Waypoints this should be supplied if possible, although there are
     * workarounds provided if it's not.
     *
     * @return An additional identifier for an airport, a VOR, an NDB or a waypoint.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Returns an additional identifier for when requesting data to differentiate between waypoint/VOR/NDB when there
     * are overlapping ICAO/Region identifiers. Should be null for other facility types like airports.
     *
     * @return Additional type identifier od null.
     */
    public FacilityType getType() {
        return type;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                ", requestID=" + requestID +
                ", icao='" + icao + '\'' +
                ", region='" + region + '\'' +
                ", type=" + type +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}