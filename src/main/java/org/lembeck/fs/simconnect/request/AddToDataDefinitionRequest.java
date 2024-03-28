package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class AddToDataDefinitionRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000000c;

    private final int defineID;
    private final String datumName;
    private final String unitsName;
    private final int datumType;
    private final float epsilon;
    private final int datumID;

    AddToDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        datumName = SimUtil.readString(buffer, 256);
        unitsName = SimUtil.readString(buffer, 256);
        datumType = buffer.getInt();
        epsilon = buffer.getFloat();
        datumID = buffer.getInt();
    }

    public AddToDataDefinitionRequest(int defineID, String datumName, String unitsName, int datumType, float epsilon, int datumID) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.datumName = datumName;
        this.unitsName = unitsName;
        this.datumType = datumType;
        this.epsilon = epsilon;
        this.datumID = datumID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, datumName, 256);
        SimUtil.writeString(outBuffer, unitsName, 256);
        outBuffer.putInt(datumType);
        outBuffer.putFloat(epsilon);
        outBuffer.putInt(datumID);
    }

    public int getDefineID() {
        return defineID;
    }

    public String getDatumName() {
        return datumName;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public int getDatumType() {
        return datumType;
    }

    public float getEpsilon() {
        return epsilon;
    }

    public int getDatumID() {
        return datumID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", defineID='" + defineID + "'" +
                ", datumName='" + datumName + "'"+
                ", unitsName='" + unitsName + "'"+
                ", datumType=" + datumType +
                ", fEpsilon=" + epsilon +
                ", datumId=" + datumID +
                "}";
    }
}
