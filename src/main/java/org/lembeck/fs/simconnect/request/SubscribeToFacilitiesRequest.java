package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class SubscribeToFacilitiesRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000041;

    private final FacilityListType facilitiesListType;
    private final int requestId;

    SubscribeToFacilitiesRequest(ByteBuffer buffer) {
        super(buffer);
        facilitiesListType = FacilityListType.values()[buffer.getInt()];
        requestId = buffer.getInt();
    }

    public SubscribeToFacilitiesRequest(FacilityListType facilitiesListType, int requestId) {
        super(TYPE_ID);
        this.facilitiesListType = facilitiesListType;
        this.requestId = requestId;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(facilitiesListType.ordinal());
        outBuffer.putInt(requestId);
    }

    public FacilityListType getFacilitiesListType() {
        return facilitiesListType;
    }

    public int getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", facilitiesListType=" + facilitiesListType +
                ", requestId=" + requestId +
                "}";
    }
}