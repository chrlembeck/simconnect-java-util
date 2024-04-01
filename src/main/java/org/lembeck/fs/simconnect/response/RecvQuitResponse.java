package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class RecvQuitResponse extends SimResponse {

    RecvQuitResponse(ByteBuffer buffer) {
        super(buffer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                "}";
    }
}