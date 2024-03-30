package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class ClearClientDataDefinitionRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003a;

    private final int defineID;

    ClearClientDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
    }

    public ClearClientDataDefinitionRequest(int defineID) {
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