package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_NDB_LIST structure is used to return a list of SIMCONNECT_DATA_FACILITY_NDB structures.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_NDB_LIST.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_NDB_LIST.htm</a>
 */
public class RecvNdbListResponse extends RecvFacilitiesListResponse {

    /**
     * Array of SIMCONNECT_DATA_FACILITY_NDB structures.
     */
    protected final FacilityNDB[] ndbList;

    RecvNdbListResponse(ByteBuffer buffer) {
        super(buffer);
        ndbList = new FacilityNDB[arraySize];
        for (int i = 0; i < arraySize; i++) {
            String icao = SimUtil.readString(buffer, 6);
            String regionCode = SimUtil.readString(buffer, 3);
            double latitude = buffer.getDouble();
            double longitude = buffer.getDouble();
            double altitude = buffer.getDouble();
            float magVar = buffer.getFloat();
            int frequency = buffer.getInt();
            ndbList[i] = new FacilityNDB(icao, regionCode, latitude, longitude, altitude, magVar, frequency);
        }
    }

    /**
     * Returns the Array of SIMCONNECT_DATA_FACILITY_NDB structures.
     *
     * @return Array of SIMCONNECT_DATA_FACILITY_NDB structures.
     */
    public FacilityNDB[] getNdbList() {
        return ndbList;
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
                ", requestID=" + requestID +
                ", arraySize=" + arraySize +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                ", ndbList=" + Arrays.toString(ndbList) +
                "}";
    }
}