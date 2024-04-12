package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestFacilitesList function is used to request a list of all the facilities of a given type
 * currently held in the reality bubble facilities cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitiesList_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitiesList_EX1.htm</a>
 */
public class RequestFacilitiesListEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000049;

    private final FacilityListType type;

    private final int requestID;

    RequestFacilitiesListEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_RequestFacilitesList function is used to request a list of all the facilities of a given type
     * currently held in the reality bubble facilities cache.
     *
     * @param type      Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param requestID Specifies the client defined request ID. This will be returned along with the data.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitiesList_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitiesList_EX1.htm</a>
     */
    public RequestFacilitiesListEx1Request(FacilityListType type, int requestID) {
        super(TYPE_ID);
        this.type = type;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.putInt(requestID);
    }

    /**
     * Returns the type of the facilities.
     *
     * @return Type of the facilities.
     */
    public FacilityListType getType() {
        return type;
    }

    /**
     * Returns the client defined request ID. This will be returned along with the data.
     *
     * @return Client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", requestID=" + requestID +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}