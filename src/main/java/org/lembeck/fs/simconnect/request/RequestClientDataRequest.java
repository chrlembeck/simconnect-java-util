package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.SimconnectPeriod;

import java.nio.ByteBuffer;

public class RequestClientDataRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003b;
    private final int clientDataID;
    private final int requestID;
    private final int defineID;
    private final SimconnectPeriod period;
    private final int flags;
    private final int origin;
    private final int interval;
    private final int limit;

    RequestClientDataRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        requestID = buffer.getInt();
        defineID = buffer.getInt();
        period = SimconnectPeriod.ofId(buffer.getInt());
        flags = buffer.getInt();
        origin = buffer.getInt();
        interval = buffer.getInt();
        limit = buffer.getInt();
    }

    public RequestClientDataRequest(int clientDataID, int requestID, int defineID, SimconnectPeriod period, int flags, int origin, int interval, int limit) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.requestID = requestID;
        this.defineID = defineID;
        this.period = period;
        this.flags = flags;
        this.origin = origin;
        this.interval = interval;
        this.limit = limit;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(requestID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(period.ordinal());
        outBuffer.putInt(flags);
        outBuffer.putInt(origin);
        outBuffer.putInt(interval);
        outBuffer.putInt(limit);
    }

    public int getClientDataID() {
        return clientDataID;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getDefineID() {
        return defineID;
    }

    public SimconnectPeriod getPeriod() {
        return period;
    }

    public int getFlags() {
        return flags;
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
        return getClass().getSimpleName() + "{" +
                "clientDataID=" + clientDataID +
                ", requestID=" + requestID +
                ", defineID=" + defineID +
                ", period=" + period +
                ", flags=" + flags +
                ", origin=" + origin +
                ", interval=" + interval +
                ", limit=" + limit +
                '}';
    }
}