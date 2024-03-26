package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class RequestDataOnSimObjectRequest extends SimRequest {

    private final int dataRequestID;
    private final int dataDefinitionID;
    private final int objectID;
    private final SimconnectPeriod period;
    private final int dataRequestFlags;
    private final int origin;
    private final int interval;
    private final int limit;

    public RequestDataOnSimObjectRequest(ByteBuffer buffer) {
        super(buffer);
        dataRequestID = buffer.getInt();
        dataDefinitionID = buffer.getInt();
        objectID = buffer.getInt();
        period = SimconnectPeriod.values()[buffer.getInt()];
        dataRequestFlags = buffer.getInt();
        origin = buffer.getInt();
        interval = buffer.getInt();
        limit = buffer.getInt();
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataRequestID);
        outBuffer.putInt(dataDefinitionID);
        outBuffer.putInt(objectID);
        outBuffer.putInt(period.ordinal());
        outBuffer.putInt(dataRequestFlags);
        outBuffer.putInt(origin);
        outBuffer.putInt(interval);
        outBuffer.putInt(limit);
    }

    public int getDataRequestID() {
        return dataRequestID;
    }

    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    public int getObjectID() {
        return objectID;
    }

    public SimconnectPeriod getPeriod() {
        return period;
    }

    public int getDataRequestFlags() {
        return dataRequestFlags;
    }

    public int getOrigin() {
        return origin;
    }

    public int getInterval() {
        return interval;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataRequestID=" + dataRequestID +
                ", dataDefinitionID=" + dataDefinitionID +
                ", objectID=" + objectID +
                ", period=" + period +
                ", dataRequestFlags=" + dataRequestFlags +
                ", interval=" + interval +
                ", limit=" + limit +
                "}";
    }
}