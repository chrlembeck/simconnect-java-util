package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class MapClientDataNameToIDRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000037;

    private final String clientDataName;

    private final int clientDataID;

    MapClientDataNameToIDRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataName = SimUtil.readString(buffer, 256);
        clientDataID = buffer.getInt();
    }

    public MapClientDataNameToIDRequest(String clientDataName, int clientDataID) {
        super(TYPE_ID);
        this.clientDataName = clientDataName;
        this.clientDataID = clientDataID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, clientDataName, 256);
        outBuffer.putInt(clientDataID);
    }

    public String getClientDataName() {
        return clientDataName;
    }

    public int getClientDataID() {
        return clientDataID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "clientDataName='" + clientDataName + '\'' +
                ", clientDataID=" + clientDataID +
                '}';
    }
}