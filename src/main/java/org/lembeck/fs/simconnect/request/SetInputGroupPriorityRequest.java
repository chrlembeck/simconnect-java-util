package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class SetInputGroupPriorityRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000012;

    private final int groupID;
    private final int priority;

    SetInputGroupPriorityRequest(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        priority = buffer.getInt();
    }

    public SetInputGroupPriorityRequest(int groupID, int priority) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.priority = priority;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        outBuffer.putInt(priority);
    }

    public int getGroupID() {
        return groupID;
    }

    public int getPriority() {
        return priority;
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
                ", priority=" + priority +
                "}";
    }
}