package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class AISetAircraftFlightPlanRequest extends SimRequest {

    public static final int TYPE_ID = 0x0000002d;

    private final int objectID;

    private final String flightPlanPath;

    private final int requestID;

    AISetAircraftFlightPlanRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        flightPlanPath = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        requestID = buffer.getInt();
    }

    public AISetAircraftFlightPlanRequest(int objectID, String flightPlanPath, int requestID) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.flightPlanPath = flightPlanPath;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        SimUtil.writeString(outBuffer, flightPlanPath, SimUtil.MAX_PATH);
        outBuffer.putInt(requestID);
    }

    public int getObjectID() {
        return objectID;
    }

    public String getFlightPlanPath() {
        return flightPlanPath;
    }

    public int getRequestID() {
        return requestID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "objectID=" + objectID +
                ", flightPlanPath='" + flightPlanPath + '\'' +
                ", requestID=" + requestID +
                '}';
    }
}