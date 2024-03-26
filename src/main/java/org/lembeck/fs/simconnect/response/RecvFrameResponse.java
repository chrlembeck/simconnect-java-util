package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

public class RecvFrameResponse extends RecvEventResponse {

    private final float frameRate;

    private final float simSpeed;

    RecvFrameResponse(ByteBuffer buffer) {
        super(buffer);
        frameRate = buffer.getFloat();
        simSpeed = buffer.getFloat();
    }

    public float getFrameRate() {
        return frameRate;
    }

    public float getSimSpeed() {
        return simSpeed;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", groupID=" + (groupID == UNKNOWN_GROUP ? "UNKNOWN_GROUP" : groupID) +
                ", eventID=" + eventID +
                ", data=" + data +
                ", frameRate=" + frameRate +
                ", simSpeed=" + simSpeed +
                "}";
    }
}