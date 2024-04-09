package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class SetInputGroupStateRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000015;

    private final int groupID;

    private final int state;

    SetInputGroupStateRequest(ByteBuffer buffer) {
        super(buffer);
        this.groupID = buffer.getInt();
        this.state = buffer.getInt();
    }

    public SetInputGroupStateRequest(int groupID, int state) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.state = state;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        outBuffer.putInt(state);
    }

    public int getGroupID() {
        return groupID;
    }

    public int getState() {
        return state;
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
                ", identifier=" + getIdentifier() +
                ", groupID=" + groupID +
                ", state=" + state +
                "}";
    }
}