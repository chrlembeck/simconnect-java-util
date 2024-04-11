package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * Represents a request that is new and not yet implemented in this framework. Will only be used by proxying
 * simconnect interface communication.
 */
public class UnknownRequest extends SimRequest {

    private final byte[] data;

    UnknownRequest(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.put(data);
    }

    /**
     * Returns the data that was contained in the request.
     *
     * @return The requests contained data.
     */
    public byte[] getData() {
        return data;
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
                ", identifier=" + getIdentifier() +
                ", data=" + SimUtil.byteArrayToString(data) +
                "}";
    }
}