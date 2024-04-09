package org.lembeck.fs.simconnect.response;

public class FacilityNDB extends FacilityWaypoint {

    protected final int frequency;

    public FacilityNDB(String icao, String regionCode, double latitude, double longitude, double altitude, float magVar, int frequency) {
        super(icao, regionCode, latitude, longitude, altitude, magVar);
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "icao='" + icao + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", magVar=" + magVar +
                ", frequency=" + frequency +
                '}';
    }
}