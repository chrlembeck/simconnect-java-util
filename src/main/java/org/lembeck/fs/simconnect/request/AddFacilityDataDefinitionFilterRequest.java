package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SimConnect_AddFacilityDataDefinitionFilter function is used add a filter on a node in the
 * FacilityDataDefinition to block sending data according to this filter, thus reduce the amount of data received
 * and limit it to only that which is required.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm</a>
 */
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

    /**
     * The SimConnect_AddFacilityDataDefinitionFilter function is used add a filter on a node in the
     * FacilityDataDefinition to block sending data according to this filter, thus reduce the amount of data received
     * and limit it to only that which is required.
     *
     * @param defineID   Specifies the ID of the client defined data definition.
     * @param filterPath Defines the node and member that you wish to apply the filter to.
     * @param filterData Filter data as bytes (will be cast to the right type later).
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm</a>
     */
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

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the node and member that you wish to apply the filter to.
     *
     * @return Node and member that you wish to apply the filter to.
     */
    public String getFilterPath() {
        return filterPath;
    }

    /**
     * Returns the filter data as bytes (will be cast to the right type later).
     *
     * @return Filter data as bytes (will be cast to the right type later).
     */
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