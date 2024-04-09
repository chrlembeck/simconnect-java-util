package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class EnumerateInputEventsRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
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

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}