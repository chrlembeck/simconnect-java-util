package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.constants.ExceptionType;
import java.nio.ByteBuffer;

public class RecvExceptionResponse extends SimResponse {

    private final int exceptionTypeIdx;
    private final int sendID;
    private final int index;

    RecvExceptionResponse(ByteBuffer buffer) {
        super(buffer);
        exceptionTypeIdx = buffer.getInt();
        sendID = buffer.getInt();
        index = buffer.getInt();
    }

    public int getExceptionTypeIdx() {
        return exceptionTypeIdx;
    }

    public int getSendID() {
        return sendID;
    }

    public int getIndex() {
        return index;
    }

    public ExceptionType getExceptionType() {
        return ExceptionType.ofId(exceptionTypeIdx);
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
                ", exceptionTypeIdx=" + exceptionTypeIdx +
                ", exceptionType=" + getExceptionType() +
                ", sendID=" + sendID +
                ", index=" + index +
                "}";
    }
}