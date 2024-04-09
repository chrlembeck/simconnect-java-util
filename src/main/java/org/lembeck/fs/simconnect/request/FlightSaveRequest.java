package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class FlightSaveRequest extends SimRequest {

    public static final int TYPE_ID = 0xf000003e;

    private final String filename;

    private final String description;

    private final int flags;
    private final String title;

    FlightSaveRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        title = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        description = SimUtil.readString(buffer, 2048);
        flags = buffer.getInt();
    }

    public FlightSaveRequest(String filename, String title, String description, int flags) {
        super(TYPE_ID);
        this.filename = filename;
        this.title = title;
        this.description = description;
        this.flags = flags;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, filename, SimUtil.MAX_PATH);
        SimUtil.writeString(outBuffer, title, SimUtil.MAX_PATH);
        SimUtil.writeString(outBuffer, description, 2048);
        outBuffer.putInt(flags);
    }

    public String getFilename() {
        return filename;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "filename='" + filename + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", flags=" + flags +
                '}';
    }
}