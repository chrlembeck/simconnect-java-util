package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class RecvSystemStateResponse extends SimResponse {

    private final int requestID;
    private final int intValue;
    private final float floatValue;
    private final String stringValue;

    RecvSystemStateResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        intValue = buffer.getInt();
        floatValue = buffer.getFloat();
        stringValue = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    public int getRequestID() {
        return requestID;
    }

    public int getIntValue() {
        return intValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", requestID=" + requestID +
                ", intValue=" + intValue +
                ", floatValue=" + floatValue +
                ", stringValue=" + stringValue +
                "}";
    }
}