package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class SubscribeToSystemEventRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000017;

    private final int clientEventID;

    private final String eventName;

    SubscribeToSystemEventRequest(ByteBuffer buffer) {
        super(buffer);
        this.clientEventID = buffer.getInt();
        this.eventName = SimUtil.readString(buffer, 256);
    }

    public SubscribeToSystemEventRequest(int clientEventID, String eventName) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
        this.eventName = eventName;
    }

    public SubscribeToSystemEventRequest(int eventID, SystemEventName eventName) {
        this(eventID, eventName.getEventName());
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
        SimUtil.writeString(outBuffer, eventName, 256);
    }

    public int getClientEventID() {
        return clientEventID;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", clientEventID=" + clientEventID +
                ", eventName='" + eventName + "'" +
                "}";
    }
}