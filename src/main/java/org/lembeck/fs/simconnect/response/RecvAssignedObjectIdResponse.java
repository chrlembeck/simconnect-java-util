package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_ASSIGNED_OBJECT_ID structure is used to return an object ID that matches a request ID.
 */
public class RecvAssignedObjectIdResponse extends SimResponse {

    /**
     * Double word containing the client defined request ID.
     */
    private final int requestId;

    /**
     * Double word containing the server defined object ID.
     */
    private final int objectId;

    RecvAssignedObjectIdResponse(ByteBuffer buffer) {
        super(buffer);
        requestId = buffer.getInt();
        objectId = buffer.getInt();
    }

    /**
     * Returns the Double word containing the client defined request ID.
     *
     * @return Double word containing the client defined request ID.
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Returns the Double word containing the server defined object ID.
     *
     * @return Double word containing the server defined object ID.
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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