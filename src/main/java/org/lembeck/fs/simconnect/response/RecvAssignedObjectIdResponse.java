package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_ASSIGNED_OBJECT_ID structure is used to return an object ID that matches a request ID.
 */
public class RecvAssignedObjectIdResponse extends SimResponse {

    private final int requestId;
    private final int objectId;

    RecvAssignedObjectIdResponse(ByteBuffer buffer) {
        super(buffer);
        requestId = buffer.getInt();
        objectId = buffer.getInt();
    }

    public int getRequestId() {
        return requestId;
    }

    public int getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", requestId=" + requestId +
                ", objectId=" + objectId +
                "}";
    }
}