package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_SetNotificationGroupPriority function is used to set the priority for a notification group.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm</a>
 */
public class SetNotificationGroupPriorityRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static int TYPE_ID = 0xf0000009;

    private final int notificationGroupID;
    private final Priority priority;

    SetNotificationGroupPriorityRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        priority = new Priority(buffer.getInt());
    }

    /**
     * The SimConnect_SetNotificationGroupPriority function is used to set the priority for a notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param priority            The group's priority. See the explanation of SimConnect Priorities.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm</a>
     */
    public SetNotificationGroupPriorityRequest(int notificationGroupID, Priority priority) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
        this.priority = priority;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(priority.getPriorityValue());
    }

    /**
     * Returns the ID of the client defined group.
     *
     * @return ID of the client defined group.
     */
    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    /**
     * Returns the group's priority. See the explanation of SimConnect Priorities.
     *
     * @return Group's priority. See the explanation of SimConnect Priorities.
     */
    public Priority getPriority() {
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