package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.SystemState;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestSystemState function is used to request information from a number of Microsoft Flight Simulator
 * system components.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_RequestSystemState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_RequestSystemState.htm</a>
 */
public class RequestSystemStateRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000035;

    private final int requestID;

    private final String state;

    RequestSystemStateRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        state = SimUtil.readString(buffer, 256);
    }

    /**
     * Creates a Request Object with the client generated request id and  the state as string.
     *
     * @param requestID The client defined request ID.
     * @param state     String identifying the system function.
     * @see SystemState#getStateName()
     */
    public RequestSystemStateRequest(int requestID, String state) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.state = state;
    }

    /**
     * Creates a Request Object with the client generated request id and  the state as enum.
     *
     * @param requestID The client defined request ID.
     * @param state     Enum identifying the system function.
     */
    public RequestSystemStateRequest(int requestID, SystemState state) {
        this(requestID, state.getStateName());
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
        SimUtil.writeString(outBuffer, state, 256);
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
                ", requestID=" + requestID +
                ", state=" + state +
                "}";
    }
}