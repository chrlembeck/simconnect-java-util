package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_AddClientEventToNotificationGroup function is used to add an individual client defined event to a
 * notification group.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm</a>
 */
public class AddClientEventToNotificationGroupRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
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

    /**
     * The SimConnect_AddClientEventToNotificationGroup function is used to add an individual client defined event to a
     * notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param clientEventID       Specifies the ID of the client defined event.
     * @param maskable            True indicates that the event will be masked by this client and will not be
     *                            transmitted to any more clients, possibly including Microsoft Flight Simulator itself
     *                            (if the priority of the client exceeds that of Flight Simulator).
     *                            False is the default. See the explanation of SimConnect Priorities.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm</a>
     */
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

    /**
     * Returns the ID of the client defined group.
     *
     * @return ID of the client defined group.
     */
    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    /**
     * Returns the ID of the client defined event.
     *
     * @return ID of the client defined event.
     */
    public int getClientEventID() {
        return clientEventID;
    }

    /**
     * Returns whether the event will be masked by this client.
     *
     * @return True indicates that the event will be masked by this client and will not be transmitted to any more
     * clients, possibly including Microsoft Flight Simulator itself (if the priority of the client exceeds that of
     * Flight Simulator). False is the default. See the explanation of SimConnect Priorities.
     */
    public boolean isMaskable() {
        return maskable;
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
                ", clientEventID=" + clientEventID +
                ", maskable=" + maskable +
                "}";
    }
}