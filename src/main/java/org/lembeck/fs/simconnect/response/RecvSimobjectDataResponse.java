package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class RecvSimobjectDataResponse extends SimResponse {

    public static final int DATA_REQUEST_FLAG_DEFAULT = 0;
    public static final int DATA_REQUEST_FLAG_CHANGED = 1;
    public static final int SIMCONNECT_DATA_REQUEST_FLAG_TAGGED = 2;
    private final int requestID;
    private final int objectID;
    private final int defineID;
    private final int flags;
    private final int entryNumber;
    private final int outOf;
    private final int defineCount;
    private final byte[] data;

    RecvSimobjectDataResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        objectID = buffer.getInt();
        defineID = buffer.getInt();
        flags = buffer.getInt();
        entryNumber = buffer.getInt();
        outOf = buffer.getInt();
        defineCount = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    public int getRequestID() {
        return requestID;
    }

    public int getObjectID() {
        return objectID;
    }

    public int getDefineID() {
        return defineID;
    }

    public int getFlags() {
        return flags;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public int getOutOf() {
        return outOf;
    }

    public int getDefineCount() {
        return defineCount;
    }

    public ByteBuffer getData() {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(LITTLE_ENDIAN);
        return buffer;
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
                ", requestID=" + requestID +
                ", objectID=" + objectID +
                ", defineID=" + defineID +
                ", flags=" + flags +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                ", defineCount=" + defineCount +
                ", data=" + SimResponse.toString(data) +
                "}";
    }
}
