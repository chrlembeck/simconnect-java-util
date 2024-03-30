package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class SetClientDataDefinitionRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003c;
    private final int clientDataID;
    private final int defineID;
    private final boolean tagged;
    private final int reserved;
    private final int dataSize;
    private final byte[] data;

    SetClientDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        defineID = buffer.getInt();
        tagged = buffer.getInt() == 1;
        reserved = buffer.getInt();
        dataSize = buffer.getInt();
        data = new byte[dataSize];
        buffer.get(data);
    }

    public SetClientDataDefinitionRequest(int clientDataID, int defineID, boolean tagged, int reserved, int dataSize, byte[] data) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.defineID = defineID;
        this.tagged = tagged;
        this.reserved = reserved;
        this.dataSize = dataSize;
        this.data = data;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(tagged ? 1 : 0);
        outBuffer.putInt(reserved);
        outBuffer.putInt(dataSize);
        outBuffer.put(data);
    }

    public int getClientDataID() {
        return clientDataID;
    }

    public int getDefineID() {
        return defineID;
    }

    public boolean isTagged() {
        return tagged;
    }

    public int getReserved() {
        return reserved;
    }

    public int getDataSize() {
        return dataSize;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "clientDataID=" + clientDataID +
                ", defineID=" + defineID +
                ", tagged=" + tagged +
                ", reserved=" + reserved +
                ", dataSize=" + dataSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}