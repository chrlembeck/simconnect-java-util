package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_QUIT is an identical structure to the SIMCONNECT_RECV structure.
 */
public class RecvQuitResponse extends SimResponse {

    RecvQuitResponse(ByteBuffer buffer) {
        super(buffer);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                "}";
    }
}