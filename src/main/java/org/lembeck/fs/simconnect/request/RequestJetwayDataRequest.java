package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class RequestJetwayDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004b;

    private final String icao;

    private final int[] indexes;

    RequestJetwayDataRequest(ByteBuffer buffer) {
        super(buffer);
        icao = SimUtil.readString(buffer, 16);
        int arrayCount = buffer.getInt();
        indexes = new int[arrayCount];
        for (int i = 0; i < arrayCount; i++) {
            indexes[i] = buffer.getInt();
        }
    }

    public RequestJetwayDataRequest(String icao, int... indexes) {
        super(TYPE_ID);
        this.icao = icao;
        this.indexes = indexes;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, icao, 16);
        outBuffer.putInt(indexes.length);
        if (indexes.length == 0) {
            outBuffer.putInt(0);
        } else {
            for (int index : indexes) {
                outBuffer.putInt(index);
            }
        }
    }

    public String getIcao() {
        return icao;
    }

    public int[] getIndexes() {
        return indexes;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "icao='" + icao + '\'' +
                ", indexes=" + Arrays.toString(indexes) +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}