package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvAirportListResponse extends RecvFacilitiesListResponse {

    private final FacilityAirport[] airportList;

    RecvAirportListResponse(ByteBuffer buffer) {
        super(buffer);
        airportList = new FacilityAirport[arraySize];
        for (int i = 0; i < arraySize; i++) {
            String icao = SimUtil.readString(buffer, 6);
            String regionCode = SimUtil.readString(buffer, 3);
            double latitude = buffer.getDouble();
            double longitude = buffer.getDouble();
            double altitude = buffer.getDouble();
            airportList[i] = new FacilityAirport(icao, regionCode, latitude, longitude, altitude);
        }
    }

    public FacilityAirport[] getAirportList() {
        return airportList;
    }

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