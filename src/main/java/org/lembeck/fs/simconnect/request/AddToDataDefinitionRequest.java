package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class AddToDataDefinitionRequest extends SimRequest {

    private final int defineID;
    private final String datumName;
    private final String unitsName;
    private final int datumType;
    private final float fEpsilon;
    private final int datumId;

    public AddToDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        datumName = SimUtil.readString(buffer, 256);
        unitsName = SimUtil.readString(buffer, 256);
        datumType = buffer.getInt();
        fEpsilon = buffer.getFloat();
        datumId = buffer.getInt();
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, datumName, 256);
        SimUtil.writeString(outBuffer, unitsName, 256);
        outBuffer.putInt(datumType);
        outBuffer.putFloat(fEpsilon);
        outBuffer.putInt(datumId);
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

    public float getfEpsilon() {
        return fEpsilon;
    }

    public int getDatumId() {
        return datumId;
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
                ", fEpsilon=" + fEpsilon +
                ", datumId=" + datumId +
                "}";
    }
}
