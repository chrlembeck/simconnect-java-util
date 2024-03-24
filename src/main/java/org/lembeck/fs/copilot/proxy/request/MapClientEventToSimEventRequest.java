package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.SimUtil;

import java.nio.ByteBuffer;

public class MapClientEventToSimEventRequest extends SimRequest {

    private final int eventID;
    private final String eventName;

    public MapClientEventToSimEventRequest(ByteBuffer buffer) {
        super(buffer);
        eventID = buffer.getInt();
        eventName = SimUtil.readString(buffer, 256);
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