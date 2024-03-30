package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class FlightPlanLoadRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003f;
    private final String filename;

    FlightPlanLoadRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    public FlightPlanLoadRequest(String filename) {
        super(TYPE_ID);
        this.filename = filename;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, filename, SimUtil.MAX_PATH);
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "filename='" + filename + '\'' +
                '}';
    }
}