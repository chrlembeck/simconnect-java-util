package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

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

    public TransmitClientEventEx1Request(int objectID, int eventID, Priority priority, int eventFlag, int data0, int data1, int data2, int data3, int data4) {
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

    public int getObjectID() {
        return objectID;
    }

    public int getEventID() {
        return eventID;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getEventFlag() {
        return eventFlag;
    }

    public int getData0() {
        return data0;
    }

    public int getData1() {
        return data1;
    }

    public int getData2() {
        return data2;
    }

    public int getData3() {
        return data3;
    }

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