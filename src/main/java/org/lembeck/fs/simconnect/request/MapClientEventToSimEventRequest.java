package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_MapClientEventToSimEvent function associates a client defined event ID with a Microsoft Flight Simulator event name.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientEventToSimEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientEventToSimEvent.htm</a>
 */
public class MapClientEventToSimEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000004;

    private final int eventID;
    private final String eventName;

    MapClientEventToSimEventRequest(ByteBuffer buffer) {
        super(buffer);
        eventID = buffer.getInt();
        eventName = SimUtil.readString(buffer, 256);
    }

    /**
     * Creates a new request instance.
     *
     * @param eventID   Specifies the ID of the client event.
     * @param eventName Specifies the Microsoft Flight Simulator event name. Refer to the Event IDs document for a list
     *                  of event names (listed under String Name). If the event name includes one or more periods (such
     *                  as "Custom.Event" in the example below) then they are custom events specified by the client, and
     *                  will only be recognized by another client (and not Microsoft Flight Simulator) that has been
     *                  coded to receive such events. No Microsoft Flight Simulator events include periods. If no entry
     *                  is made for this parameter, the event is private to the client.
     *                  Alternatively enter a decimal number in the format "#nnnn" or a hex number in the format
     *                  "#0xnnnn", where these numbers are in the range THIRD_PARTY_EVENT_ID_MIN and
     *                  THIRD_PARTY_EVENT_ID_MAX, in order to receive events from third-party add-ons to Flight
     *                  Simulator X.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
     */
    public MapClientEventToSimEventRequest(int eventID, String eventName) {
        super(TYPE_ID);
        this.eventID = eventID;
        this.eventName = eventName;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(eventID);
        SimUtil.writeString(outBuffer, eventName, 256);
    }

    /**
     * Returns the ID of the client event.
     * @return ID of the client event.
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Returns the event name.
     * @return The event name.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
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
                ", eventID='" + eventID + "'" +
                ", eventName=" + eventName +
                "}";
    }
}