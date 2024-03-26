package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class RequestNotificationGroupRequest extends SimRequest {

    private final int notificationGroupID;
    private final int reserved;
    private final int flags;

    RequestNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        reserved = buffer.getInt();
        flags = buffer.getInt();
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(reserved);
        outBuffer.putInt(flags);
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    public int getReserved() {
        return reserved;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", notificationGroupID=" + notificationGroupID +
                ", reserved=" + reserved +
                ", flags=" + flags +
                "}";
    }
}