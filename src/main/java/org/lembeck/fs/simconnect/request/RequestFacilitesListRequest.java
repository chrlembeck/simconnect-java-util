package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class RequestFacilitesListRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000043;

    private final FacilityListType facilityListType;
    private final int dataRequestId;

    RequestFacilitesListRequest(ByteBuffer buffer) {
        super(buffer);
        facilityListType = FacilityListType.values()[buffer.getInt()];
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