package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_ClearNotificationGroup function is used to remove all the client defined events from a notification group.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm</a>
 */
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

    /**
     * The SimConnect_ClearNotificationGroup function is used to remove all the client defined events from a notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group that is to have all its events removed.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm</a>
     */
    public ClearNotificationGroupRequest(int notificationGroupID) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
    }

    /**
     * Returns the ID of the client defined group that is to have all its events removed.
     *
     * @return ID of the client defined group that is to have all its events removed.
     */
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