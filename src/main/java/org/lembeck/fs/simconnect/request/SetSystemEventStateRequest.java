package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.State;

import java.nio.ByteBuffer;

public class SetSystemEventStateRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000006;
    private final int clientEventID;
    private final State state;

    SetSystemEventStateRequest(ByteBuffer buffer) {
        super(buffer);
        clientEventID = buffer.getInt();
        state = State.values()[buffer.getInt()];
    }

    public SetSystemEventStateRequest(int clientEventID, State state) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
        this.state = state;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
        outBuffer.putInt(state.ordinal());
    }

    public int getClientEventID() {
        return clientEventID;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", clientEventID=" + clientEventID +
                ", state=" + state +
                "}";
    }
}