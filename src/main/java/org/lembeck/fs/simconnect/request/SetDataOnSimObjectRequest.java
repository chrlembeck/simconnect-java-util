package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.DataSetFlag;
import org.lembeck.fs.simconnect.response.SimResponse;
import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.constants.DataSetFlag.NOT_TAGGED;
import static org.lembeck.fs.simconnect.constants.DataSetFlag.TAGGED;

public class SetDataOnSimObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000010;

    private final int dataDefinitionID;
    private final int objectID;
    private final DataSetFlag dataSetFlag;
    private final int arrayCount;
    private final int unitSize;
    private final byte[] data;

    SetDataOnSimObjectRequest(ByteBuffer buffer) {
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

    public SetDataOnSimObjectRequest(int dataDefinitionID, int objectID, DataSetFlag dataSetFlag, int arrayCount, int unitSize, byte[] data) {
        super(TYPE_ID);
        this.dataDefinitionID = dataDefinitionID;
        this.objectID = objectID;
        this.dataSetFlag = dataSetFlag;
        this.arrayCount = arrayCount;
        this.unitSize = unitSize;
        this.data = data;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataDefinitionID);
        outBuffer.putInt(objectID);
        outBuffer.putInt(dataSetFlag == TAGGED ? 1 : 0);
        outBuffer.putInt(arrayCount);
        outBuffer.putInt(unitSize);
        outBuffer.put(data);
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
                ", dataDefinitionID=" + dataDefinitionID +
                ", objectID=" + objectID +
                ", dataSetFlag=" + dataSetFlag +
                ", arrayCount=" + arrayCount +
                ", unitSize=" + unitSize +
                ", data=" + SimResponse.toString(data) +
                "}";
    }
}