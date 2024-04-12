package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;


/**
 * The SIMCONNECT_RECV_EVENT_EX1 structure is used to return an event ID to the client, along with up to 5 parameters.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_EX1.htm</a>
 */
public class RecvEventEx1Response extends SimResponse {

    private final int groupID;

    private final int eventID;

    private final int data0;

    private final int data1;

    private final int data2;

    private final int data3;

    private final int data4;

    RecvEventEx1Response(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        eventID = buffer.getInt();
        data0 = buffer.getInt();
        data1 = buffer.getInt();
        data2 = buffer.getInt();
        data3 = buffer.getInt();
        data4 = buffer.getInt();
    }

    /**
     * Returns the ID of the client defined group, or the special case value: UNKNOWN_GROUP (which equals DWORD_MAX).
     *
     * @return The ID of the client defined group.
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
     * Returns the first value that was passed along with the event.
     *
     * @return First value that was passed along with the event.
     */
    public int getData0() {
        return data0;
    }

    /**
     * Returns the second value that was passed along with the event.
     *
     * @return Second value that was passed along with the event.
     */
    public int getData1() {
        return data1;
    }

    /**
     * Returns the third value that was passed along with the event.
     *
     * @return Third value that was passed along with the event.
     */
    public int getData2() {
        return data2;
    }

    /**
     * Returns the fourth value that was passed along with the event.
     *
     * @return Fourth value that was passed along with the event.
     */
    public int getData3() {
        return data3;
    }

    /**
     * Returns the fifth value that was passed along with the event.
     *
     * @return Fifth value that was passed along with the event.
     */
    public int getData4() {
        return data4;
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
                ", data0=" + data0 +
                ", data1=" + data1 +
                ", data2=" + data2 +
                ", data3=" + data3 +
                ", data4=" + data4 +
                "}";
    }
}