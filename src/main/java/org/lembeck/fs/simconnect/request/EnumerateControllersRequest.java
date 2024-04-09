package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class EnumerateControllersRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
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

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}