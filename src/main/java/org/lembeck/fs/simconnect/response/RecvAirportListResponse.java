package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_AIRPORT_LIST structure is used to return a list of SIMCONNECT_DATA_FACILITY_AIRPORT structures.
 */
public class RecvAirportListResponse extends RecvFacilitiesListResponse {

    private final FacilityAirport[] airportList;

    RecvAirportListResponse(final ByteBuffer buffer) {
        super(buffer);
        airportList = new FacilityAirport[arraySize];
        for (int i = 0; i < arraySize; i++) {
            final String icao = SimUtil.readString(buffer, 6);
            final String regionCode = SimUtil.readString(buffer, 3);
            final double latitude = buffer.getDouble();
            final double longitude = buffer.getDouble();
            final double altitude = buffer.getDouble();
            airportList[i] = new FacilityAirport(icao, regionCode, latitude, longitude, altitude);
        }
    }

    /**
     * Returns the List of airports in this response.
     *
     * @return List of contained airports-
     */
    public FacilityAirport[] getAirportList() {
        return airportList;
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
                ", airportList=" + Arrays.toString(airportList) +
                "}";
    }
}