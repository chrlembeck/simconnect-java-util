package org.lembeck.fs.copilot.proxy.response;

import java.nio.ByteBuffer;

public class ExceptionResponse extends SimResponse {

    private final int exceptionType;
    private final int sendID;
    private final int index;

    ExceptionResponse(ByteBuffer buffer) {
        super(buffer);
        exceptionType = buffer.getInt();
        sendID = buffer.getInt();
        index = buffer.getInt();
    }

    public int getExceptionType() {
        return exceptionType;
    }

    public int getSendID() {
        return sendID;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", exceptionType=" + exceptionType +
                ", sendID=" + sendID +
                ", index=" + index +
                "}";
    }
}