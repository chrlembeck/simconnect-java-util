package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class MapClientEventToSimEventRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000004;

    private final int eventID;
    private final String eventName;

    MapClientEventToSimEventRequest(ByteBuffer buffer) {
        super(buffer);
        eventID = buffer.getInt();
        eventName = SimUtil.readString(buffer, 256);
    }

    public MapClientEventToSimEventRequest(int eventID, String eventName) {
        super(TYPE_ID);
        this.eventID = eventID;
        this.eventName = eventName;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(eventID);
        SimUtil.writeString(outBuffer, eventName, 256);
    }

    public int getEventID() {
        return eventID;
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
                ", eventID='" + eventID + "'" +
                ", eventName=" + eventName +
                "}";
    }
}