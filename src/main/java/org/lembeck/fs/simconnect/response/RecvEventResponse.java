package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT structure is used to return an event ID to the client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT.htm</a>
 */
public class RecvEventResponse extends SimResponse {

    /**
     * he ID of the client defined group, or the special case value: UNKNOWN_GROUP (which equals DWORD_MAX).
     */
    protected final int groupID;

    /**
     * The ID of the client defined event that has been requested (such as EVENT_1 or EVENT_BRAKES).
     */
    protected final int eventID;

    /**
     * This value is usually zero, but some events require further qualification. For example, joystick movement events
     * require a movement value in addition to the notification that the joystick has been moved
     * (see SimConnect_MapInputEventToClientEvent for more information).
     */
    protected final int data;

    RecvEventResponse(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        eventID = buffer.getInt();
        data = buffer.getInt();
    }

    /**
     * Returns the ID of the client defined group, or the special case value: UNKNOWN_GROUP (which equals DWORD_MAX).
     *
     * @return ID of the client defined group.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns the ID of the client defined event that has been requested (such as EVENT_1 or EVENT_BRAKES).
     *
     * @return ID of the client defined event that has been requested.
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * This value is usually zero, but some events require further qualification. For example, joystick movement events
     * require a movement value in addition to the notification that the joystick has been moved
     * (see SimConnect_MapInputEventToClientEvent for more information).
     *
     * @return Value that was passed along this event.
     */
    public int getData() {
        return data;
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
                ", groupID=" + (groupID == UNKNOWN_GROUP ? "UNKNOWN_GROUP" : groupID) +
                ", eventID=" + eventID +
                ", data=" + data +
                "}";
    }
}