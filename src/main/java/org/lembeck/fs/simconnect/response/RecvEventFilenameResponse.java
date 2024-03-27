package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

public class RecvEventFilenameResponse extends RecvEventResponse {

    /**
     * The returned filename.
     */
    private final String filename;

    /**
     * Reserved, should be 0.
     */
    private final int flags;

    RecvEventFilenameResponse(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        flags = buffer.getInt();
    }

    public String getFilename() {
        return filename;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", groupID=" + (groupID == UNKNOWN_GROUP ? "UNKNOWN_GROUP" : groupID) +
                ", eventID=" + eventID +
                ", data=" + data +
                ", filename=" + filename +
                ", flags=" + flags +
                "}";
    }
}