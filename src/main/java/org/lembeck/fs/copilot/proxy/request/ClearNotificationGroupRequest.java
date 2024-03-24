package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class ClearNotificationGroupRequest extends SimRequest {

    private final int notificationGroupID;

    ClearNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", notificationGroupID=" + notificationGroupID +
                "}";
    }
}