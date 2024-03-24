package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class RemoveClientEventRequest extends SimRequest {

    private final int notificationGroupID;

    private final int clientEventID;

    RemoveClientEventRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        clientEventID = buffer.getInt();
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    public int getClientEventID() {
        return clientEventID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", notificationGroupID=" + notificationGroupID +
                ", clientEventID=" + clientEventID +
                "}";
    }
}