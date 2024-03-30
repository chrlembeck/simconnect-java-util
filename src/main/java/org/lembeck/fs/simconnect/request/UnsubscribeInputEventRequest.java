package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class UnsubscribeInputEventRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000053;
    private final long hash;

    UnsubscribeInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        hash = buffer.getLong();
    }

    public UnsubscribeInputEventRequest(long hash) {
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "hash=" + hash +
                '}';
    }
}