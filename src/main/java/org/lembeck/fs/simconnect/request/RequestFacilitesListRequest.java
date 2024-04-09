package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

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

    public FacilityListType getFacilityListType() {
        return facilityListType;
    }

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