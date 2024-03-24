package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.SimUtil;
import java.nio.ByteBuffer;

public class AddToDataDefinitionRequest extends SimRequest {

    private final int dataDefinitionId;
    private final String datumName;
    private final String unitsName;
    private final int datumType;
    private final float fEpsilon;
    private final int datumId;

    public AddToDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionId = buffer.getInt();
        datumName = SimUtil.readString(buffer, 256);
        unitsName = SimUtil.readString(buffer, 256);
        datumType = buffer.getInt();
        fEpsilon = buffer.getFloat();
        datumId = buffer.getInt();
    }

    public int getDataDefinitionId() {
        return dataDefinitionId;
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
                " {type: " + Integer.toHexString(getTypeId()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", datumName='" + datumName + "'"+
                ", unitsName='" + unitsName + "'"+
                ", datumType=" + datumType +
                ", fEpsilon=" + fEpsilon +
                ", datumId=" + datumId +
                "}";
    }
}
