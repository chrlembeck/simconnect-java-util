package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class SetSystemEventStateRequest extends SimRequest {

    private final int clientEventID;
    private final State state;

    SetSystemEventStateRequest(ByteBuffer buffer) {
        super(buffer);
        clientEventID = buffer.getInt();
        state = State.values()[buffer.getInt()];
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