package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.EventFlag;
import java.nio.ByteBuffer;

/**
 * The SimConnect_TransmitClientEvent_EX1 function is used to request that the Microsoft Flight Simulator server
 * transmit to all SimConnect clients the specified client event. This function is specifically designed to permit
 * the sending of multiple parameters for the key event (up to five), unlike the SimConnect_TransmitClientEvent
 * which only permits one.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm</a>
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
 */
public class TransmitClientEventEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000044;

    private final int objectID;
    private final int eventID;
    private final Priority priority;
    private final int eventFlag;
    private final int data0;
    private final int data1;
    private final int data2;
    private final int data3;
    private final int data4;

    TransmitClientEventEx1Request(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        eventID = buffer.getInt();
        priority = new Priority(buffer.getInt());
        eventFlag = buffer.getInt();
        data0 = buffer.getInt();
        data1 = buffer.getInt();
        data2 = buffer.getInt();
        data3 = buffer.getInt();
        data4 = buffer.getInt();
    }

    /**
     * The SimConnect_TransmitClientEvent_EX1 function is used to request that the Microsoft Flight Simulator server
     * transmit to all SimConnect clients the specified client event. This function is specifically designed to permit
     * the sending of multiple parameters for the key event (up to five), unlike the SimConnect_TransmitClientEvent
     * which only permits one.
     *
     * @param objectID  Specifies the ID of the server defined object. If this parameter is set to
     *                  SIMCONNECT_OBJECT_ID_USER, then the transmitted event will be sent to the other clients in
     *                  priority order. If this parameters contains another object ID, then the event will be sent
     *                  direct to that sim-object, and no other clients will receive it.
     * @param eventID   Specifies the ID of the client event.
     * @param priority  This specifies the priority to send the message to all clients with this priority. To receive
     *                  the event notification other SimConnect clients must have subscribed to receive the event. See
     *                  the explanation of SimConnect Priorities. The exception to the default behavior is set by the
     *                  SIMCONNECT_EVENT_FLAG_GROUPID_IS_PRIORITY flag, described below.
     * @param eventFlag One or more of the flags shown in {@link EventFlag}.
     * @param data0     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data1     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data2     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data3     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data4     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
     */
    public TransmitClientEventEx1Request(int objectID, int eventID, Priority priority, int eventFlag, int data0,
            int data1, int data2, int data3, int data4) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.eventID = eventID;
        this.priority = priority;
        this.eventFlag = eventFlag;
        this.data0 = data0;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        outBuffer.putInt(eventID);
        outBuffer.putInt(priority.getPriorityValue());
        outBuffer.putInt(eventFlag);
        outBuffer.putInt(data0);
        outBuffer.putInt(data1);
        outBuffer.putInt(data2);
        outBuffer.putInt(data3);
        outBuffer.putInt(data4);
    }

    /**
     * Returns the server defined object id of this event.
     *
     * @return The ID of the server defined object. If this parameter is set to SIMCONNECT_OBJECT_ID_USER, then the
     * transmitted event will be sent to the other clients in priority order. If this parameters contains another object
     * ID, then the event will be sent direct to that sim-object, and no other clients will receive it.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Returns the ID of the client event.
     *
     * @return ID of the client event.
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Returns the priority of this event.
     *
     * @return Priority of this event.
     * @see Priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the flags that were defined for this event.
     *
     * @return Flags that were defined for this event.
     * @see EventFlag
     */
    public int getEventFlag() {
        return eventFlag;
    }

    /**
     * Returns the first number required by this event.
     *
     * @return The first number required by this event.
     */
    public int getData0() {
        return data0;
    }

    /**
     * Returns the second number required by this event.
     *
     * @return The second number required by this event.
     */
    public int getData1() {
        return data1;
    }

    /**
     * Returns the third number required by this event.
     *
     * @return The third number required by this event.
     */
    public int getData2() {
        return data2;
    }

    /**
     * Returns the fourth number required by this event.
     *
     * @return The fourth number required by this event.
     */
    public int getData3() {
        return data3;
    }

    /**
     * Returns the fifth number required by this event.
     *
     * @return The fifth number required by this event.
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
                ", identifier=" + getIdentifier() +
                ", objectID='" + objectID + "'" +
                ", eventID=" + eventID +
                ", priority=" + priority +
                ", eventFlag=" + eventFlag +
                ", data0=" + data0 +
                ", data1=" + data1 +
                ", data2=" + data2 +
                ", data3=" + data3 +
                ", data4=" + data4 +
                "}";
    }
}