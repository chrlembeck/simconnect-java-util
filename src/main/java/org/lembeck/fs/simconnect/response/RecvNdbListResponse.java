package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvNdbListResponse extends RecvFacilitiesListResponse {

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

    public FacilityNDB[] getNdbList() {
        return ndbList;
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
                ", ndbList=" + Arrays.toString(ndbList) +
                "}";
    }
}