package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class AddClientEventToNotificationGroupRequest extends SimRequest {

    private final int notificationGroupID;
    private final int clientEventID;
    private final boolean maskable;

    AddClientEventToNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        clientEventID = buffer.getInt();
        maskable = buffer.getInt() != 0;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(clientEventID);
        outBuffer.putInt(maskable ? 1 : 0);

    }

    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    public int getClientEventID() {
        return clientEventID;
    }

    public boolean isMaskable() {
        return maskable;
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
                ", maskable=" + maskable +
                "}";
    }
}