package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * Class to represent new and currently not yet implemented response types that will be received from the simulators
 * simconnect interface.
 */
public class UnknownResponse extends SimResponse {

    private final byte[] data;

    UnknownResponse(ByteBuffer buffer) {
        super(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    /**
     * Returns the contained data of the unknown response.
     *
     * @return Data of the unknown response.
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
        return getClass().getSimpleName() + " {Typ: " + Integer.toHexString(getTypeID()) + ", Länge=" + getSize() + ", Version=" + getVersion() + ", data=" + SimUtil.byteArrayToString(data) + "}";
    }
}