package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_UnsubscribeFromSystemEvent function is used to request that notifications are no longer received for
 * the specified system event.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_UnsubscribeFromSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_UnsubscribeFromSystemEvent.htm</a>
 */
public class UnsubscribeFromSystemEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000018;

    private final int clientEventID;

    UnsubscribeFromSystemEventRequest(ByteBuffer buffer) {
        super(buffer);
        this.clientEventID = buffer.getInt();
    }

    /**
     * The SimConnect_UnsubscribeFromSystemEvent function is used to request that notifications are no longer received
     * for the specified system event.
     *
     * @param clientEventID Specifies the ID of the client event.
     */
    public UnsubscribeFromSystemEventRequest(int clientEventID) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
    }

    /**
     * Returns the ID of the client event.
     *
     * @return ID of the client event.
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
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", clientEventID=" + clientEventID +
                "}";
    }
}