package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.SystemState;
import java.nio.ByteBuffer;

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

    public RequestSystemStateRequest(int requestID, String state) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.state = state;
    }

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