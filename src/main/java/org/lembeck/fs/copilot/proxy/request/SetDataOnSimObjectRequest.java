package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.lembeck.fs.copilot.proxy.request.DataSetFlag.NOT_TAGGED;
import static org.lembeck.fs.copilot.proxy.request.DataSetFlag.TAGGED;

public class SetDataOnSimObjectRequest extends SimRequest {

    private final int dataDefinitionID;
    private final int objectID;
    private final DataSetFlag dataSetFlag;
    private final int arrayCount;
    private final int unitSize;
    private final byte[] data;

    public SetDataOnSimObjectRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionID = buffer.getInt();
        objectID = buffer.getInt();
        dataSetFlag = buffer.getInt() == 0 ? NOT_TAGGED : TAGGED;
        int arrayCountTemp = buffer.getInt();
        arrayCount = arrayCountTemp == 0 ? 1 : arrayCountTemp;
        unitSize = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    public int getObjectID() {
        return objectID;
    }

    public DataSetFlag getDataSetFlag() {
        return dataSetFlag;
    }

    public int getArrayCount() {
        return arrayCount;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataDefinitionID=" + dataDefinitionID +
                ", objectID=" + objectID +
                ", dataSetFlag=" + dataSetFlag +
                ", arrayCount=" + arrayCount +
                ", unitSize=" + unitSize +
                ", data=" + Arrays.toString(data) +
                "}";
    }
}