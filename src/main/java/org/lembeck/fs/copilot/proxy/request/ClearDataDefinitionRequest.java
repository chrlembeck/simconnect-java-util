package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class ClearDataDefinitionRequest extends SimRequest {

    private final int dataDefinitionID;

    ClearDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionID = buffer.getInt();
    }

    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataDefinitionID=" + dataDefinitionID +
                "}";
    }
}