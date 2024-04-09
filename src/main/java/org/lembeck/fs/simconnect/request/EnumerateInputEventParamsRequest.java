package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class EnumerateInputEventParamsRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000054;
    private final long hash;

    EnumerateInputEventParamsRequest(ByteBuffer buffer) {
        super(buffer);
        hash = buffer.getLong();
    }

    public EnumerateInputEventParamsRequest(long hash) {
        super(TYPE_ID);
        this.hash = hash;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putLong(hash);
    }

    public long getHash() {
        return hash;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "hash=" + hash +
                '}';
    }
}