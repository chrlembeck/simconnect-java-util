package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class AICreateEnrouteATCAircraftRequest extends SimRequest {

    public static final int TYPE_ID = 0x00000028;

    private final String containerTitle;

    private final String tailNumber;

    private final int flightNumber;

    private final String flightPlanPath;

    private final float flightPlanPosition;

    private final boolean touchAndGo;

    private final int requestID;

    AICreateEnrouteATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        flightNumber = buffer.getInt();
        flightPlanPath = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        flightPlanPosition = buffer.getFloat();
        touchAndGo = buffer.getInt() != 0;
        requestID = buffer.getInt();
    }

    public AICreateEnrouteATCAircraftRequest(String containerTitle, String tailNumber, int flightNumber, String flightPlanPath, float flightPlanPosition, boolean touchAndGo, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.flightNumber = flightNumber;
        this.flightPlanPath = flightPlanPath;
        this.flightPlanPosition = flightPlanPosition;
        this.touchAndGo = touchAndGo;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        outBuffer.putInt(flightNumber);
        SimUtil.writeString(outBuffer, flightPlanPath, SimUtil.MAX_PATH);
        outBuffer.putFloat(flightPlanPosition);
        outBuffer.putInt(touchAndGo ? 1 : 0);
        outBuffer.putInt(requestID);
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getFlightPlanPath() {
        return flightPlanPath;
    }

    public float getFlightPlanPosition() {
        return flightPlanPosition;
    }

    public boolean isTouchAndGo() {
        return touchAndGo;
    }

    public int getRequestID() {
        return requestID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", containerTitle='" + containerTitle + "'" +
                ", tailNumber='" + tailNumber + "'" +
                ", flightNumber=" + flightNumber +
                ", flightPlanPath='" + flightPlanPath + "'" +
                ", flightPlanPosition=" + flightPlanPosition +
                ", touchAndGo=" + touchAndGo +
                ", requestID=" + requestID +
                "}";
    }
}