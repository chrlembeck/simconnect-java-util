package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class AIReleaseControlRequest extends SimRequest {

    public static final int TYPE_ID = 0x0000002b;

    private final int objectID;

    private final int requestID;

    AIReleaseControlRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        requestID = buffer.getInt();
    }

    public AIReleaseControlRequest(int objectID, int requestID) {
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "objectID=" + objectID +
                ", requestID=" + requestID +
                '}';
    }
}