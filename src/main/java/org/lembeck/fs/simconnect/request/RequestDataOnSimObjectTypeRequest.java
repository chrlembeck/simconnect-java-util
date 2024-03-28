package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class RequestDataOnSimObjectTypeRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000000f;

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

    public RequestDataOnSimObjectTypeRequest(int requestID, int defineID, int radiusMeters, SimObjectType type) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.defineID = defineID;
        this.radiusMeters = radiusMeters;
        this.type = type;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(radiusMeters);
        outBuffer.putInt(type.ordinal());
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