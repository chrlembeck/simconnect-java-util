package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestNotificationGroup function is used to request events are transmitted from a notification
 * group, when the simulation is in Dialog Mode.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm</a>
 */
public class RequestNotificationGroupRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000b;

    private final int notificationGroupID;

    private final int reserved;

    private final int flags;

    RequestNotificationGroupRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        reserved = buffer.getInt();
        flags = buffer.getInt();
    }

    /**
     * The SimConnect_RequestNotificationGroup function is used to request events are transmitted from a notification
     * group, when the simulation is in Dialog Mode.
     *
     * @param notificationGroupID Specifies the ID of the client defined input group that is to have all its events removed.
     * @param reserved            Reserved for future use.
     * @param flags               Reserved for future use.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm</a>
     */
    public RequestNotificationGroupRequest(int notificationGroupID, int reserved, int flags) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
        this.reserved = reserved;
        this.flags = flags;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(reserved);
        outBuffer.putInt(flags);
    }

    /**
     * Returns the ID of the client defined input group that is to have all its events removed.
     *
     * @return ID of the client defined input group that is to have all its events removed.
     */
    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    /**
     * Reserved for future use.
     *
     * @return Reserved for future use.
     */
    public int getReserved() {
        return reserved;
    }

    /**
     * Reserved for future use.
     *
     * @return Reserved for future use.
     */
    public int getFlags() {
        return flags;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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