package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class FlightLoadRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003d;

    private final String filename;

    FlightLoadRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    public FlightLoadRequest(String filename) {
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