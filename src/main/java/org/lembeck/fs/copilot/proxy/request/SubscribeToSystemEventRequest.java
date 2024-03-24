package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.SimUtil;
import java.nio.ByteBuffer;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class SubscribeToSystemEventRequest extends SimRequest {

    private final int clientEventID;

    private final String eventName;

    public SubscribeToSystemEventRequest(ByteBuffer buffer) {
        super(buffer);
        clientEventID = buffer.getInt();
        this.eventName = SimUtil.readString(buffer, 256);
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
                " {Typ: " + Integer.toHexString(getTypeId()) +
                ", Länge=" + getSize() +
                ", Version=" + getVersion() +
                ", clientEventID=" + clientEventID +
                ", eventName='" + eventName + "'" +
                "}";
    }

}