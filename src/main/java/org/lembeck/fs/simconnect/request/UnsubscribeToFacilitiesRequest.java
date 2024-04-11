package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_UnsubscribeToFacilities function is used to request that notifications of additions to the facilities
 * cache are no longer sent.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm</a>
 */
public class UnsubscribeToFacilitiesRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000042;

    private final FacilityListType facilityListType;

    UnsubscribeToFacilitiesRequest(ByteBuffer buffer) {
        super(buffer);
        facilityListType = FacilityListType.ofId(buffer.getInt());
    }

    /**
     * The SimConnect_UnsubscribeToFacilities function is used to request that notifications of additions to the
     * facilities cache are no longer sent.
     *
     * @param facilityListType Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm</a>
     */
    public UnsubscribeToFacilitiesRequest(FacilityListType facilityListType) {
        super(TYPE_ID);
        this.facilityListType = facilityListType;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(facilityListType.ordinal());
    }

    /**
     * Returns the type of facilities no further additions to the cache should be sent for.
     *
     * @return The type of facilities no further additions to the cache should be sent for.
     */
    public FacilityListType getFacilityListType() {
        return facilityListType;
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
                ", facilityListType=" + facilityListType +
                "}";
    }
}