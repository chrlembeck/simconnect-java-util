package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class SetNotificationGroupPriorityRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static int TYPE_ID = 0xf0000009;

    private final int notificationGroupID;
    private final int priority;

    SetNotificationGroupPriorityRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        priority = buffer.getInt();
    }

    public SetNotificationGroupPriorityRequest(int notificationGroupID, int priority) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
        this.priority = priority;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(priority);
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
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
                ", notificationGroupID=" + notificationGroupID +
                ", priority=" + priority +
                "}";
    }
}