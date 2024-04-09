package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class AICreateNonATCAircraftRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x00000029;

    private final String containerTitle;

    private final String tailNumber;

    private final InitPosition initPosition;
    private final int requestID;

    AICreateNonATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        initPosition = new InitPosition(buffer);
        requestID = buffer.getInt();
    }

    public AICreateNonATCAircraftRequest(String containerTitle, String tailNumber, InitPosition initPosition, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.initPosition = initPosition;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        initPosition.write(outBuffer);
        outBuffer.putInt(requestID);
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public InitPosition getInitPosition() {
        return initPosition;
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
                ", initPosition=" + initPosition +
                ", requestID=" + requestID +
                '}';
    }
}