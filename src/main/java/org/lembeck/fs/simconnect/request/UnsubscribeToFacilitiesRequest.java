package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

public class UnsubscribeToFacilitiesRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
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
                ", facilitiesListType=" + facilitiesListType +
                "}";
    }
}