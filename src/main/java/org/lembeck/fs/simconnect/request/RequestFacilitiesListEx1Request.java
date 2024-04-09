package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

public class RequestFacilitiesListEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000049;

    private final FacilityListType type;

    private final int requestID;

    RequestFacilitiesListEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        requestID = buffer.getInt();
    }

    public RequestFacilitiesListEx1Request(FacilityListType type, int requestID) {
        super(TYPE_ID);
        this.type = type;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.putInt(requestID);
    }

    public FacilityListType getType() {
        return type;
    }

    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", requestID=" + requestID +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}