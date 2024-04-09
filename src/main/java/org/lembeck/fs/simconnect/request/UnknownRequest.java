package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.response.SimResponse;

import java.nio.ByteBuffer;

public class UnknownRequest extends SimRequest {

    private final byte[] data;

    public UnknownRequest(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.put(data);
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
                ", data=" + SimResponse.toString(data) +
                "}";
    }
}