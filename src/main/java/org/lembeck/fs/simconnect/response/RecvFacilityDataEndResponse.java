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

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}