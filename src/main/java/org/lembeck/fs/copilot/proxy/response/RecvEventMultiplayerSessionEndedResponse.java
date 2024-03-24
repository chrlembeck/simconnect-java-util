package org.lembeck.fs.copilot.proxy.response;

import java.nio.ByteBuffer;

public class RecvEventMultiplayerSessionEndedResponse extends SimResponse {
    RecvEventMultiplayerSessionEndedResponse(ByteBuffer buffer) {
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