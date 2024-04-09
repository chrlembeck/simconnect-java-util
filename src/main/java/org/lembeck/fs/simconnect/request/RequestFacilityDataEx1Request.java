package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class RequestFacilityDataEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004a;

    public enum FacilityType {
        VOR, NDB, WAYPOINT;

        public byte toByte() {
            return switch (this) {
                case NDB -> 78;
                case VOR -> 86;
                case WAYPOINT -> 87;
            };
        }
    }

    private final int defineID;
    private final int requestID;

    private final String icao;
    private final String region;
    private final FacilityType type;

    RequestFacilityDataEx1Request(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        requestID = buffer.getInt();
        icao = SimUtil.readString(buffer, 16);
        region = SimUtil.readString(buffer, 4);
        type = switch (buffer.get()) {
            case 78 -> FacilityType.NDB;
            case 86 -> FacilityType.VOR;
            case 87 -> FacilityType.WAYPOINT;
            default -> null;
        };

    }

    public RequestFacilityDataEx1Request(int defineID, int requestID, String icao, String region, FacilityType type) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.requestID = requestID;
        this.icao = icao;
        this.region = region;
        this.type = type;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(requestID);
        SimUtil.writeString(outBuffer, icao, 16);
        SimUtil.writeString(outBuffer, region, 4);
        outBuffer.put(type == null ? 0 : type.toByte());
    }

    public int getDefineID() {
        return defineID;
    }

    public int getRequestID() {
        return requestID;
    }

    public String getIcao() {
        return icao;
    }

    public String getRegion() {
        return region;
    }

    public FacilityType getType() {
        return type;
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
                ", type=" + type +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}