package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.EventFlag;
import java.nio.ByteBuffer;

/**
 * The SimConnect_TransmitClientEvent function is used to request that the Microsoft Flight Simulator server transmit to all SimConnect clients the specified client event.
 */
public class TransmitClientEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000005;

    private final int objectID;

    private final int clientEventID;

    private final int data;

    private final Priority priority;

    private final int eventFlag;


    TransmitClientEventRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        clientEventID = buffer.getInt();
        data = buffer.getInt();
        priority = new Priority(buffer.getInt());
        eventFlag = buffer.getInt();
    }

    /**
     * The SimConnect_TransmitClientEvent function is used to request that the Microsoft Flight Simulator server transmit to all SimConnect clients the specified client event.
     *
     * @param objectID      Specifies the ID of the server defined object. If this parameter is set to SIMCONNECT_OBJECT_ID_USER, then the transmitted event will be sent to the other clients in priority order. If this parameters contains another object ID, then the event will be sent direct to that sim-object, and no other clients will receive it.
     * @param clientEventID Specifies the ID of the client event.
     * @param data          Double word containing any additional number required by the event. This is often zero. If the event is a Microsoft Flight Simulator event, then refer to the Event IDs document for information on this additional value. If the event is a custom event, then any value put in this parameter will be available to the clients that receive the event.
     * @param priority      This specifies the priority to send the message to all clients with this priority. To receive the event notification other SimConnect clients must have subscribed to receive the event. See the explanation of SimConnect Priorities. The exception to the default behavior is set by the SIMCONNECT_EVENT_FLAG_GROUPID_IS_PRIORITY flag, described below.
     * @param eventFlag     One or more of the flags shown in {@link EventFlag}.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     * @see EventFlag
     */
    public TransmitClientEventRequest(int objectID, int clientEventID, int data, Priority priority, int eventFlag) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.clientEventID = clientEventID;
        this.data = data;
        this.priority = priority;
        this.eventFlag = eventFlag;
    }


    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        outBuffer.putInt(clientEventID);
        outBuffer.putInt(data);
        outBuffer.putInt(priority.getPriorityValue());
        outBuffer.putInt(eventFlag);
    }

    /**
     * Returns the ID of the server defined object.
     *
     * @return ID of the server defined object.
     */
    public int getObjectID() {
        return objectID;
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
     * Returns the double word containing any additional number required by the event.
     *
     * @return double word containing any additional number required by the event.
     */
    public int getData() {
        return data;
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
                ", objectID=" + objectID +
                ", clientEventID=" + clientEventID +
                ", data=" + data +
                ", priority=" + priority +
                ", eventFlag=" + eventFlag +
                "}";
    }
}