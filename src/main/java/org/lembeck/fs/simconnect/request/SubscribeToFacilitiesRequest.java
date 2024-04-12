package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_SubscribeToFacilities function is used to request notifications when a facility of a certain type is
 * added to the facilities cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities.htm</a>
 */
public class SubscribeToFacilitiesRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000041;

    private final FacilityListType facilityListType;

    private final int requestId;

    SubscribeToFacilitiesRequest(ByteBuffer buffer) {
        super(buffer);
        facilityListType = FacilityListType.ofId(buffer.getInt());
        requestId = buffer.getInt();
    }

    /**
     * The SimConnect_SubscribeToFacilities function is used to request notifications when a facility of a certain type
     * is added to the facilities cache.
     *
     * @param facilityListType Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param requestId        Specifies the client defined request ID. This will be returned along with the data.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities.htm</a>
     */
    public SubscribeToFacilitiesRequest(FacilityListType facilityListType, int requestId) {
        super(TYPE_ID);
        this.facilityListType = facilityListType;
        this.requestId = requestId;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(facilityListType.ordinal());
        outBuffer.putInt(requestId);
    }

    /**
     * Returns the type of the facilities.
     *
     * @return Type of the facilities.
     */
    public FacilityListType getFacilityListType() {
        return facilityListType;
    }

    /**
     * Returns the client defined request ID. This will be returned along with the data.
     *
     * @return Client defined request ID.
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", facilitiesListType=" + facilityListType +
                ", requestId=" + requestId +
                "}";
    }
}