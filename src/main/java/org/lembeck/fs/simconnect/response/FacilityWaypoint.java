package org.lembeck.fs.simconnect.response;

public class FacilityWaypoint extends FacilityAirport {

    protected final float magVar;

    public FacilityWaypoint(String icao, String regionCode, double latitude, double longitude, double altitude, float magVar) {
        super(icao, regionCode, latitude, longitude, altitude);
        this.magVar = magVar;
    }

    public float getMagVar() {
        return magVar;
    }

    @Override
    public String toString() {
        return "FacilityAirport{" +
                "icao='" + icao + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", magVar=" + magVar +
                '}';
    }
}