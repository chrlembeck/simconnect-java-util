package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class RequestReservedKeyRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000016;

    private final int eventID;

    private final String keyChoice1;
    private final String keyChoice2;
    private final String keyChoice3;

    RequestReservedKeyRequest(ByteBuffer buffer) {
        super(buffer);
        eventID = buffer.getInt();
        keyChoice1 = SimUtil.readString(buffer, 30);
        keyChoice2 = SimUtil.readString(buffer, 30);
        keyChoice3 = SimUtil.readString(buffer, 30);
    }

    public RequestReservedKeyRequest(int eventID, String keyChoice1, String keyChoice2, String keyChoice3) {
        super(TYPE_ID);
        this.eventID = eventID;
        this.keyChoice1 = keyChoice1;
        this.keyChoice2 = keyChoice2;
        this.keyChoice3 = keyChoice3;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(eventID);
        SimUtil.writeString(outBuffer, keyChoice1, 30);
        SimUtil.writeString(outBuffer, keyChoice2, 30);
        SimUtil.writeString(outBuffer, keyChoice3, 30);
    }

    public int getEventID() {
        return eventID;
    }

    public String getKeyChoice1() {
        return keyChoice1;
    }

    public String getKeyChoice2() {
        return keyChoice2;
    }

    public String getKeyChoice3() {
        return keyChoice3;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", eventID=" + eventID +
                ", keyChoice1'=" + keyChoice1 + "'" +
                ", keyChoice2'=" + keyChoice2 + "'" +
                ", keyChoice3'=" + keyChoice3 + "'" +
                "}";
    }
}