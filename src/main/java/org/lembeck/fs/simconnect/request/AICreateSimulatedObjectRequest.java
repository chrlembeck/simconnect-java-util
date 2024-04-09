package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class AICreateSimulatedObjectRequest extends SimRequest {

    public static final int TYPE_ID = 0x0000002a;

    private final String containerTitle;

    private final InitPosition initPosition;

    private final int requestID;

    AICreateSimulatedObjectRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        initPosition = new InitPosition(buffer);
        requestID = buffer.getInt();
    }

    public AICreateSimulatedObjectRequest(String containerTitle, InitPosition initPosition, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.initPosition = initPosition;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        initPosition.write(outBuffer);
        outBuffer.putInt(requestID);
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public InitPosition getInitPosition() {
        return initPosition;
    }

    public int getRequestID() {
        return requestID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "containerTitle='" + containerTitle + '\'' +
                ", initPosition=" + initPosition +
                ", requestID=" + requestID +
                '}';
    }
}