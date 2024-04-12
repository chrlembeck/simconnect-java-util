package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.SystemState;
import java.nio.ByteBuffer;

/**
 * The setSystemState method is used to access a number of Flight Simulator system components.
 */
public class SetSystemStateRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000036;

    private final String state;

    private final int intParam;

    private final float floatParam;

    private final String stringParam;

    SetSystemStateRequest(ByteBuffer buffer) {
        super(buffer);
        this.state = SimUtil.readString(buffer, 256);
        this.intParam = buffer.getInt();
        this.floatParam = buffer.getFloat();
        this.stringParam = SimUtil.readString(buffer, 256);
    }

    /**
     * The setSystemState method is used to access a number of Flight Simulator system components.
     *
     * @param state       The system function.
     * @param intParam    An integer value, set depending on the value of state.
     * @param floatParam  A float value, set depending on the value of state.
     * @param stringParam A string value, set depending on the value of state.
     */
    public SetSystemStateRequest(SystemState state, int intParam, float floatParam, String stringParam) {
        super(TYPE_ID);
        this.state = state.getStateName();
        this.intParam = intParam;
        this.floatParam = floatParam;
        this.stringParam = stringParam;
    }

    /**
     * The setSystemState method is used to access a number of Flight Simulator system components.
     *
     * @param state       A string identifying the system function.
     * @param intParam    An integer value, set depending on the value of state.
     * @param floatParam  A float value, set depending on the value of state.
     * @param stringParam A string value, set depending on the value of state.
     */
    public SetSystemStateRequest(String state, int intParam, float floatParam, String stringParam) {
        super(TYPE_ID);
        this.state = state;
        this.intParam = intParam;
        this.floatParam = floatParam;
        this.stringParam = stringParam;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, state, 256);
        outBuffer.putInt(intParam);
        outBuffer.putFloat(floatParam);
        SimUtil.writeString(outBuffer, stringParam, 256);
    }

    /**
     * Returns A string identifying the system function.
     *
     * @return The system function.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the integer value, set depending on the value of state.
     *
     * @return Integer value.
     */
    public int getIntParam() {
        return intParam;
    }

    /**
     * Returns the float value, set depending on the value of state.
     *
     * @return Float value.
     */
    public float getFloatParam() {
        return floatParam;
    }

    /**
     * Returns the string value, set depending on the value of state.
     *
     * @return String value.
     */
    public String getStringParam() {
        return stringParam;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", requestID='" + state + "'" +
                ", intParam=" + intParam +
                ", floatParam=" + floatParam +
                ", stringParam='" + stringParam + "'" +
                "}";
    }
}