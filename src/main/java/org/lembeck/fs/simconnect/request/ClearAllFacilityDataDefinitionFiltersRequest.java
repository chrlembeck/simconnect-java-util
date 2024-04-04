package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class ClearAllFacilityDataDefinitionFiltersRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000056;

    private final int defineID;

    ClearAllFacilityDataDefinitionFiltersRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
    }

    public ClearAllFacilityDataDefinitionFiltersRequest(int defineID) {
        super(TYPE_ID);
        this.defineID = defineID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
    }

    public int getDefineID() {
        return defineID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                '}';
    }
}