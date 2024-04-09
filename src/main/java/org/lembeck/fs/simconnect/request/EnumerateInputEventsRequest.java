package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class EnumerateInputEventsRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000004f;
    private final int requestID;

    EnumerateInputEventsRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
    }

    public EnumerateInputEventsRequest(int requestID) {
        super(TYPE_ID);
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
    }

    public int getRequestID() {
        return requestID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}