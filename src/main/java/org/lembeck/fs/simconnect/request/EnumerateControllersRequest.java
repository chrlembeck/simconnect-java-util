package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class EnumerateControllersRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000004c;


    EnumerateControllersRequest(ByteBuffer buffer) {
        super(buffer);
    }

    public EnumerateControllersRequest() {
        super(TYPE_ID);
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        // nothing to write here
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}