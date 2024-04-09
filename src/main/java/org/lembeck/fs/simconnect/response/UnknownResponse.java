package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class UnknownResponse extends SimResponse {

    private final byte[] data;

    public UnknownResponse(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " {Typ: " + Integer.toHexString(getTypeID()) + ", Länge=" + getSize() + ", Version=" + getVersion() + ", data=" + toString(data) + "}";
    }
}