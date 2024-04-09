package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class UnsubscribeFromSystemEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000018;

    private final int clientEventID;

    UnsubscribeFromSystemEventRequest(ByteBuffer buffer) {
        super(buffer);
        this.clientEventID = buffer.getInt();
    }

    public UnsubscribeFromSystemEventRequest(int clientEventID) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
    }

    public int getClientEventID() {
        return clientEventID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", clientEventID=" + clientEventID +
                "}";
    }
}