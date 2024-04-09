package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class AIRemoveObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x0000002c;

    private final int objectID;

    private final int requestID;

    AIRemoveObjectRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        requestID = buffer.getInt();
    }

    public AIRemoveObjectRequest(int objectID, int requestID) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        outBuffer.putInt(requestID);
    }

    public int getObjectID() {
        return objectID;
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
                "objectID=" + objectID +
                ", requestID=" + requestID +
                '}';
    }
}