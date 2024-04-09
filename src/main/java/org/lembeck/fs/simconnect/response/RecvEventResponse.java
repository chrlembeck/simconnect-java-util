package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

public class RecvEventResponse extends SimResponse {

    protected final int groupID;
    protected final int eventID;
    protected final int data;

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