package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

public class UnsubscribeToFacilitiesEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    private static final int TYPE_ID = 0xf0000048;

    private final FacilityListType type;
    private final boolean unsubscribeNewInRange;
    private final boolean unsubscribeOldOutRange;

    UnsubscribeToFacilitiesEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        unsubscribeNewInRange = buffer.get() != 0;
        unsubscribeOldOutRange = buffer.get() != 0;
    }

    public UnsubscribeToFacilitiesEx1Request(FacilityListType type, boolean unsubscribeNewInRange, boolean unsubscribeOldOutRange) {
        super(TYPE_ID);
        this.type = type;
        this.unsubscribeNewInRange = unsubscribeNewInRange;
        this.unsubscribeOldOutRange = unsubscribeOldOutRange;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.put(unsubscribeNewInRange ? (byte) 1 : (byte) 0);
        outBuffer.put(unsubscribeOldOutRange ? (byte) 1 : (byte) 0);
    }

    public FacilityListType getType() {
        return type;
    }

    public boolean isUnsubscribeNewInRange() {
        return unsubscribeNewInRange;
    }

    public boolean isUnsubscribeOldOutRange() {
        return unsubscribeOldOutRange;
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
                ", unsubscribeNewInRange=" + unsubscribeNewInRange +
                ", unsubscribeOldOutRange=" + unsubscribeOldOutRange +
                '}';
    }
}