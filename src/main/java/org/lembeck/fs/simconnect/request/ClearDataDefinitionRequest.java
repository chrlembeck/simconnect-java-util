package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class ClearDataDefinitionRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000000d;

    private final int dataDefinitionID;

    ClearDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionID = buffer.getInt();
    }

    public ClearDataDefinitionRequest(int dataDefinitionID) {
        super(TYPE_ID);
        this.dataDefinitionID = dataDefinitionID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataDefinitionID);
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