package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class RecvFacilityDataEndResponse extends SimResponse {

    private final int requestID;

    public RecvFacilityDataEndResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
    }

    public int getRequestID() {
        return requestID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}