package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class TransmitClientEventRequest extends SimRequest {
    private final int objectID;
    private final int clientEventID;
    private final int data;
    private final int notificationGroupID;
    private final int eventFlag;

    TransmitClientEventRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        clientEventID = buffer.getInt();
        data = buffer.getInt();
        notificationGroupID = buffer.getInt();
        eventFlag = buffer.getInt();
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        outBuffer.putInt(clientEventID);
        outBuffer.putInt(data);
        outBuffer.putInt(notificationGroupID);
        outBuffer.putInt(eventFlag);
    }

    public int getObjectID() {
        return objectID;
    }

    public int getClientEventID() {
        return clientEventID;
    }

    public int getData() {
        return data;
    }

    public int getNotificationGroupID() {
        return notificationGroupID;
    }

    public int getEventFlag() {
        return eventFlag;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", objectID=" + objectID +
                ", clientEventID=" + clientEventID +
                ", data=" + data +
                ", notificationGroupID=" + notificationGroupID +
                ", eventFlag=" + eventFlag +
                "}";
    }
}