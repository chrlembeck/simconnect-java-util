package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestFacilitesList function is used to request a list of all the facilities of a given type
 * currently in the world, or within a radius of the aircraft depending on the requested type.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm</a>
 */
public class RequestFacilitesListRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000043;

    private final FacilityListType facilityListType;
    private final int dataRequestId;

    RequestFacilitesListRequest(ByteBuffer buffer) {
        super(buffer);
        facilityListType = FacilityListType.ofId(buffer.getInt());
        dataRequestId = buffer.getInt();
    }

    /**
     * The SimConnect_RequestFacilitesList function is used to request a list of all the facilities of a given type
     * currently in the world, or within a radius of the aircraft depending on the requested type.
     *
     * @param facilityListType Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param requestId        Specifies the client defined request ID. This will be returned along with the data.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm</a>
     */
    public RequestFacilitesListRequest(FacilityListType facilityListType, int requestId) {
        super(TYPE_ID);
        this.dataRequestId = requestId;
        this.facilityListType = facilityListType;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(facilityListType.ordinal());
        outBuffer.putInt(dataRequestId);
    }

    /**
     * Returns one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     *
     * @return Member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     */
    public FacilityListType getFacilityListType() {
        return facilityListType;
    }

    /**
     * Returns the client defined request ID.
     * @return The client defined request ID.
     */
    public int getDataRequestId() {
        return dataRequestId;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", facilityListType=" + facilityListType +
                ", dataRequestId=" + dataRequestId +
                "}";
    }
}