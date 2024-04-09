package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class ClearNotificationGroupRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000a;

    private final int notificationGroupID;

    ClearNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
    }

    public ClearNotificationGroupRequest(int notificationGroupID) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
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
                "}";
    }
}