package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class RecvListTemplate extends SimResponse {

    protected final int requestID;
    protected final int arraySize;
    protected final int entryNumber;
    protected final int outOf;

    RecvListTemplate(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        arraySize = buffer.getInt();
        entryNumber = buffer.getInt();
        outOf = buffer.getInt();
    }

    public int getRequestID() {
        return requestID;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public int getOutOf() {
        return outOf;
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
                ", arraySize=" + arraySize +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                '}';
    }
}