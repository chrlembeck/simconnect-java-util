package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_ClearAllFacilityDataDefinitionFilters function is used to clear all applied facility definition filters.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm</a>
 */
public class ClearAllFacilityDataDefinitionFiltersRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000056;

    private final int defineID;

    ClearAllFacilityDataDefinitionFiltersRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
    }

    /**
     * The SimConnect_ClearAllFacilityDataDefinitionFilters function is used to clear all applied facility definition filters.
     *
     * @param defineID Specifies the ID of the client defined data definition.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm</a>
     */
    public ClearAllFacilityDataDefinitionFiltersRequest(int defineID) {
        super(TYPE_ID);
        this.defineID = defineID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
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
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                '}';
    }
}