package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class AddFacilityDataDefinitionFilterRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000055;

    private final int defineID;

    private final String filterPath;

    private final byte[] filterData;

    AddFacilityDataDefinitionFilterRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        filterPath = SimUtil.readString(buffer, 256);
        int filterDataSize = buffer.getInt();
        filterData = new byte[filterDataSize];
        buffer.get(filterData);
    }

    public AddFacilityDataDefinitionFilterRequest(int defineID, String filterPath, byte[] filterData) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.filterPath = filterPath;
        this.filterData = filterData;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, filterPath, 256);
        outBuffer.putInt(filterData.length);
        outBuffer.put(filterData);
    }

    public int getDefineID() {
        return defineID;
    }

    public String getFilterPath() {
        return filterPath;
    }

    public byte[] getFilterData() {
        return filterData;
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
                ", filterPath='" + filterPath + '\'' +
                ", filterData=" + Arrays.toString(filterData) +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}