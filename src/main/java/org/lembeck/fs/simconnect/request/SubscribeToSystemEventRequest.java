package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.SystemEventName;

import java.nio.ByteBuffer;

/**
 * The SimConnect_SubscribeToSystemEvent function is used to request that a specific system event is notified to
 * the client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
 */
public class SubscribeToSystemEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000017;

    private final int clientEventID;

    private final String eventName;

    SubscribeToSystemEventRequest(ByteBuffer buffer) {
        super(buffer);
        this.clientEventID = buffer.getInt();
        this.eventName = SimUtil.readString(buffer, 256);
    }

    /**
     * The SimConnect_SubscribeToSystemEvent function is used to request that a specific system event is notified to
     * the client.
     *
     * @param clientEventID Specifies the ID of the client event.
     * @param eventName     The string name for the requested system event (note that the event names are not
     *                      case-sensitive). Unless otherwise stated in the Description, notifications of the event are
     *                      returned in a SIMCONNECT_RECV_EVENT structure (identify the event from the EventID given
     *                      with this function).
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
     */
    public SubscribeToSystemEventRequest(int clientEventID, String eventName) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
        this.eventName = eventName;
    }

    /**
     * The SimConnect_SubscribeToSystemEvent function is used to request that a specific system event is notified to
     * the client.
     *
     * @param eventID   Specifies the ID of the client event.
     * @param eventName The system event (note that the event names are not case-sensitive). Unless otherwise stated in
     *                  the Description, notifications of the event are returned in a SIMCONNECT_RECV_EVENT structure
     *                  (identify the event from the EventID given with this function).
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
     */
    public SubscribeToSystemEventRequest(int eventID, SystemEventName eventName) {
        this(eventID, eventName.getEventName());
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
        SimUtil.writeString(outBuffer, eventName, 256);
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
     * Returns the requested system event name.
     *
     * @return Requested system event name.
     */
    public String getEventName() {
        return eventName;
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
                ", eventName='" + eventName + "'" +
                "}";
    }
}