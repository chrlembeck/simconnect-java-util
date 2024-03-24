package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class RequestFacilitesListRequest extends SimRequest {

    private final FacilityListType facilityListType;
    private final int dataRequestId;

    RequestFacilitesListRequest(ByteBuffer buffer) {
        super(buffer);
        facilityListType = FacilityListType.values()[buffer.getInt()];
        dataRequestId = buffer.getInt();
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