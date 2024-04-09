package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class RequestFacilityDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000046;
    private final int defineID;
    private final int requestID;
    private final String icao;
    private final String region;

    RequestFacilityDataRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        requestID = buffer.getInt();
        icao = SimUtil.readString(buffer, 16);
        region = SimUtil.readString(buffer, 4);
    }

    public RequestFacilityDataRequest(int defineID, int requestID, String icao, String region) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.requestID = requestID;
        this.icao = icao;
        this.region = region;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(requestID);
        SimUtil.writeString(outBuffer, icao, 16);
        SimUtil.writeString(outBuffer, region, 4);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                ", requestID=" + requestID +
                ", icao='" + icao + '\'' +
                ", region='" + region + '\'' +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}