package org.lembeck.fs.copilot.proxy.response;

import java.nio.ByteBuffer;

public class UnknownResponse extends SimResponse {

    private byte[] data;

    public UnknownResponse(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {Typ: " + Integer.toHexString(getTypeId()) + ", Länge=" + getSize() + ", Version=" + getVersion() + ", data=" + toString(data) + "}";
    }
}