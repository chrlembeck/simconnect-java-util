package org.lembeck.fs.copilot.proxy.request;

import java.nio.ByteBuffer;

public class RequestDataOnSimObjectTypeRequest extends SimRequest {

    private final int requestID;
    private final int defineID;
    private final int radiusMeters;
    private final SimObjectType type;

    RequestDataOnSimObjectTypeRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        defineID = buffer.getInt();
        radiusMeters = buffer.getInt();
        type = SimObjectType.values()[buffer.getInt()];
    }

    public int getRequestID() {
        return requestID;
    }

    public int getDefineID() {
        return defineID;
    }

    public int getRadiusMeters() {
        return radiusMeters;
    }

    public SimObjectType getType() {
        return type;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", requestID='" + requestID + "'" +
                ", defineID=" + defineID +
                ", radiusMeters=" + radiusMeters +
                ", type=" + type +
                "}";
    }
}