package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.response.SimResponse;

import java.nio.ByteBuffer;

public class UnknownRequest extends SimRequest {

    private final byte[] data;

    public UnknownRequest(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
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