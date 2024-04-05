package org.lembeck.fs.simconnect.response;


import org.lembeck.fs.simconnect.constants.SimObjectType;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

public class RecvEventObjectAddRemoveResponse extends RecvEventResponse {

    private final SimObjectType type;

    RecvEventObjectAddRemoveResponse(ByteBuffer buffer) {
        super(buffer);
        type = SimObjectType.ofId(buffer.getInt());
    }

    public SimObjectType getType() {
        return type;
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
                ", type=" + type +
                "}";
    }
}