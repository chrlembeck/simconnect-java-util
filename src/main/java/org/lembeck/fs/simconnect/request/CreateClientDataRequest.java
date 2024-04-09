package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class CreateClientDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000038;

    private final int clientDataID;
    private final int dataSize;
    private final boolean readonly;

    CreateClientDataRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        dataSize = buffer.getInt();
        readonly = buffer.getInt() != 0;
    }

    public CreateClientDataRequest(int clientDataID, int dataSize, boolean readonly) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.dataSize = dataSize;
        this.readonly = readonly;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(dataSize);
        outBuffer.putInt(readonly ? 1 : 0);
    }

    public int getClientDataID() {
        return clientDataID;
    }

    public int getDataSize() {
        return dataSize;
    }

    public boolean isReadonly() {
        return readonly;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "clientDataID=" + clientDataID +
                ", dataSize=" + dataSize +
                ", readonly=" + readonly +
                '}';
    }
}