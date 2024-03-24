package org.lembeck.fs.copilot.proxy.response;

import java.nio.ByteBuffer;

public class RecvEventResponse extends SimResponse {

    public static final int UNKNOWN_GROUP = 0xffffffff;
    private final int groupID;
    private final int eventID;
    private final int data;

    RecvEventResponse(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        eventID = buffer.getInt();
        data = buffer.getInt();
    }

    public int getGroupID() {
        return groupID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getData() {
        return data;
    }

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