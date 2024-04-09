package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

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

    public int getGroupID() {
        return groupID;
    }

    public int getEventID() {
        return eventID;
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