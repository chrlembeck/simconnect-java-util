package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class GetInputEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000050;

    private final int requestID;

    private final long hash;

    GetInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        hash = buffer.getLong();
    }

    public GetInputEventRequest(int requestID, long hash) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.hash = hash;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
        outBuffer.putLong(hash);
    }

    public int getRequestID() {
        return requestID;
    }

    public long getHash() {
        return hash;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "GetInputEventRequest{" +
                "requestID=" + requestID +
                ", hash=" + hash +
                '}';
    }
}