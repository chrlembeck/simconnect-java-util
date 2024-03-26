package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class SetNotificationGroupPriorityRequest extends SimRequest {

    private final int notificationGroupID;
    private final int priority;

    SetNotificationGroupPriorityRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        priority = buffer.getInt();
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