package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class AICreateParkedATCAircraftRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x00000027;

    private final String containerTitle;

    private final String tailNumber;

    private final String airportIcaoID;

    private final int requestID;

    AICreateParkedATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        airportIcaoID = SimUtil.readString(buffer, 5);
        requestID = buffer.getInt();
    }

    public AICreateParkedATCAircraftRequest(String containerTitle, String tailNumber, String airportIcaoID, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.airportIcaoID = airportIcaoID;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        SimUtil.writeString(outBuffer, airportIcaoID, 5);
        outBuffer.putInt(requestID);
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public String getAirportIcaoID() {
        return airportIcaoID;
    }

    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "containerTitle='" + containerTitle + '\'' +
                ", tailNumber='" + tailNumber + '\'' +
                ", airportIcaoID='" + airportIcaoID + '\'' +
                ", requestID=" + requestID +
                '}';
    }
}