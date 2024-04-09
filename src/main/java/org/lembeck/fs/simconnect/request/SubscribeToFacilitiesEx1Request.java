package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

public class SubscribeToFacilitiesEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000047;
    private final FacilityListType type;
    private final int newElemInRangeRequestID;
    private final int oldElemOutRangeRequestID;

    SubscribeToFacilitiesEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        newElemInRangeRequestID = buffer.getInt();
        oldElemOutRangeRequestID = buffer.getInt();
    }

    public SubscribeToFacilitiesEx1Request(FacilityListType type, int newElemInRangeRequestID, int oldElemOutRangeRequestID) {
        super(TYPE_ID);
        this.type = type;
        this.newElemInRangeRequestID = newElemInRangeRequestID;
        this.oldElemOutRangeRequestID = oldElemOutRangeRequestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.putInt(newElemInRangeRequestID);
        outBuffer.putInt(oldElemOutRangeRequestID);
    }

    public FacilityListType getType() {
        return type;
    }

    public int getNewElemInRangeRequestID() {
        return newElemInRangeRequestID;
    }

    public int getOldElemOutRangeRequestID() {
        return oldElemOutRangeRequestID;
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
                ", newElemInRangeRequestID=" + newElemInRangeRequestID +
                ", oldElemOutRangeRequestID=" + oldElemOutRangeRequestID +
                '}';
    }
}