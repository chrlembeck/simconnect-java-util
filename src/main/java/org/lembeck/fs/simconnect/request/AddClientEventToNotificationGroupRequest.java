package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class AddClientEventToNotificationGroupRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000007;

    private final int notificationGroupID;
    private final int clientEventID;
    private final boolean maskable;

    AddClientEventToNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        clientEventID = buffer.getInt();
        maskable = buffer.getInt() != 0;
    }

    public AddClientEventToNotificationGroupRequest(int notificationGroupID, int clientEventID, boolean maskable) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
        this.clientEventID = clientEventID;
        this.maskable = maskable;
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