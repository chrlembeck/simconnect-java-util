package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RemoveClientEvent function is used to remove a client defined event from a notification group.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm</a>
 */
public class RemoveClientEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000008;

    private final int notificationGroupID;

    private final int clientEventID;

    RemoveClientEventRequest(ByteBuffer buffer) {
        super(buffer);
        notificationGroupID = buffer.getInt();
        clientEventID = buffer.getInt();
    }

    /**
     * The SimConnect_RemoveClientEvent function is used to remove a client defined event from a notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param clientEventID       Specifies the ID of the client defined event ID that is to be removed from the group.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm</a>
     */
    public RemoveClientEventRequest(int notificationGroupID, int clientEventID) {
        super(TYPE_ID);
        this.notificationGroupID = notificationGroupID;
        this.clientEventID = clientEventID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(clientEventID);
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
     * Returns the ID of the client defined event ID that is to be removed from the group.
     *
     * @return ID of the client defined event ID that is to be removed from the group.
     */
    public int getClientEventID() {
        return clientEventID;
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
                "}";
    }
}