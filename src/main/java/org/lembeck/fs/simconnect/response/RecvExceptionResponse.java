package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.constants.ExceptionType;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_EXCEPTION structure is used with the SIMCONNECT_EXCEPTION enumeration type to return information on an error that has occurred.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EXCEPTION.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EXCEPTION.htm</a>
 */
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

    /**
     * Returns the type index of the exception.
     *
     * @return Type index of the exception.
     * @see #getExceptionType()
     */
    public int getExceptionTypeIdx() {
        return exceptionTypeIdx;
    }

    /**
     * Returns the ID of the packet that contained the error.
     *
     * @return The ID of the packet that contained the error.
     */
    public int getSendID() {
        return sendID;
    }

    /**
     * Returns the index number (starting at 1) of the first parameter that caused an error.
     * Special case: UNKNOWN_INDEX = 0.
     *
     * @return index of the first parameter causing the error.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the type of the exception.
     *
     * @return Type of the exception.
     */
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