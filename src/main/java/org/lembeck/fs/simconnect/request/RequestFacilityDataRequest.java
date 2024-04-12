package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestFacilityData function is used to request data according to a predefined object, an ICAO and
 * a region.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData.htm</a>
 * @see RequestFacilityDataEx1Request
 */
public class RequestFacilityDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000046;

    private final int defineID;

    private final int requestID;

    private final String icao;

    private final String region;

    RequestFacilityDataRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        requestID = buffer.getInt();
        icao = SimUtil.readString(buffer, 16);
        region = SimUtil.readString(buffer, 4);
    }

    /**
     * The SimConnect_RequestFacilityData function is used to request data according to a predefined object, an ICAO and
     * a region.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param requestID The client defined request ID.
     * @param icao      Used to identify an airport, a VOR, an NDB or a waypoint.
     * @param region    Additional identifier for an airport, a VOR, an NDB or a waypoint. For airports, this can be
     *                  omitted without issue, however for VOR / NDB / Waypoints this should be supplied if possible,
     *                  although there are workarounds provided if it's not.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilityData.htm</a>
     * @see RequestFacilityDataEx1Request
     */
    public RequestFacilityDataRequest(int defineID, int requestID, String icao, String region) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.requestID = requestID;
        this.icao = icao;
        this.region = region;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(requestID);
        SimUtil.writeString(outBuffer, icao, 16);
        SimUtil.writeString(outBuffer, region, 4);
    }

    /**
     * Returns the  ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the client defined request ID.
     *
     * @return Client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the icao code of the facility.
     *
     * @return Icao code of the facility.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Returns the additional identifier for an airport, a VOR, an NDB or a waypoint. For airports, this can be omitted
     * without issue, however for VOR / NDB / Waypoints this should be supplied if possible, although there are
     * workarounds provided if it's not.
     *
     * @return Additional identifier for an airport, a VOR, an NDB or a waypoint.
     */
    public String getRegion() {
        return region;
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
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}