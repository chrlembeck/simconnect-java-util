package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class ClearInputGroupRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000014;

    private final int groupID;

    ClearInputGroupRequest(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
    }

    public ClearInputGroupRequest(int groupID) {
        super(TYPE_ID);
        this.groupID = groupID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
    }
}