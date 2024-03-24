package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class TransmitClientEventExRequest extends SimRequest {
    private final int objectID;
    private final int eventID;
    private final int notificationGroupID;
    private final int eventFlag;
    private final int data0;
    private final int data1;
    private final int data2;
    private final int data3;
    private final int data4;

    TransmitClientEventExRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        eventID = buffer.getInt();
        notificationGroupID = buffer.getInt();
        eventFlag = buffer.getInt();
        data0 = buffer.getInt();
        data1 = buffer.getInt();
        data2 = buffer.getInt();
        data3 = buffer.getInt();
        data4 = buffer.getInt();
    }

    public int getObjectID() {
        return objectID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", objectID='" + objectID + "'" +
                ", eventID=" + eventID +
                ", notificationGroupID=" + notificationGroupID +
                ", eventFlag=" + eventFlag +
                ", data0=" + data0 +
                ", data1=" + data1 +
                ", data2=" + data2 +
                ", data3=" + data3 +
                ", data4=" + data4 +
                "}";
    }
}