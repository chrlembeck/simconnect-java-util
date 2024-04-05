package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class UnsubscribeToFacilitiesRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000042;

    private final FacilityListType facilitiesListType;

    UnsubscribeToFacilitiesRequest(ByteBuffer buffer) {
        super(buffer);
        facilitiesListType = FacilityListType.ofId(buffer.getInt());
    }

    public UnsubscribeToFacilitiesRequest(FacilityListType facilitiesListType) {
        super(TYPE_ID);
        this.facilitiesListType = facilitiesListType;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(facilitiesListType.ordinal());
    }

    public FacilityListType getFacilitiesListType() {
        return facilitiesListType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", facilitiesListType=" + facilitiesListType +
                "}";
    }
}