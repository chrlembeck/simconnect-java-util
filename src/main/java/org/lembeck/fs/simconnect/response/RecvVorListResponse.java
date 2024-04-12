package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_VOR_LIST structure is used to return a list of SIMCONNECT_DATA_FACILITY_VOR structures.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_VOR_LIST.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_VOR_LIST.htm</a>
 */
public class RecvVorListResponse<T> extends RecvFacilitiesListResponse {

    /**
     * Array of the contained vor stations.
     */
    protected final FacilityVOR[] vorList;

    RecvVorListResponse(ByteBuffer buffer) {
        super(buffer);
        vorList = new FacilityVOR[arraySize];
        for (int i = 0; i < arraySize; i++) {
            String icao = SimUtil.readString(buffer, 6);
            String regionCode = SimUtil.readString(buffer, 3);
            double latitude = buffer.getDouble();
            double longitude = buffer.getDouble();
            double altitude = buffer.getDouble();
            float magVar = buffer.getFloat();
            int frequency = buffer.getInt();
            int flags = buffer.getInt();
            float localizer = buffer.getInt();
            double glideLat = buffer.getDouble();
            double glideLon = buffer.getDouble();
            double glideAlt = buffer.getDouble();
            float glideSlopeAngle = buffer.getFloat();
            vorList[i] = new FacilityVOR(icao, regionCode, latitude, longitude, altitude, magVar, frequency, flags, localizer, glideLat, glideLon, glideAlt, glideSlopeAngle);
        }
    }

    /**
     * Returns the contained VOR stations.
     *
     * @return The contained VOR stations.
     */
    public FacilityVOR[] getVorList() {
        return vorList;
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
                ", vorList=" + Arrays.toString(vorList) +
                "}";
    }
}