package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_SYSTEM_STATE structure is used with the SimConnect_RequestSystemState function to retrieve
 * specific Microsoft Flight Simulator systems states and information.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_SYSTEM_STATE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_SYSTEM_STATE.htm</a>
 */
public class RecvSystemStateResponse extends SimResponse {

    private final int requestID;

    private final int intValue;

    private final float floatValue;

    private final String stringValue;

    RecvSystemStateResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        intValue = buffer.getInt();
        floatValue = buffer.getFloat();
        stringValue = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    /**
     * Returns the client defined request ID.
     *
     * @return The client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the integer, or boolean, value.
     *
     * @return Integer or boolean value.
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Returne the contained float value (if any).
     *
     * @return The contained float value (if any).
     */
    public float getFloatValue() {
        return floatValue;
    }

    /**
     * Returns the contained string value (if any).
     *
     * @return The contained string value (if any).
     */
    public String getStringValue() {
        return stringValue;
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
                ", intValue=" + intValue +
                ", floatValue=" + floatValue +
                ", stringValue=" + stringValue +
                "}";
    }
}