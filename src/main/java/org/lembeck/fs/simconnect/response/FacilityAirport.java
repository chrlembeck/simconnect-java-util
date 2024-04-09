package org.lembeck.fs.simconnect.response;

public class FacilityAirport {

    protected final String icao;

    protected final String regionCode;

    protected final double latitude;

    protected final double longitude;

    protected final double altitude;

    public FacilityAirport(String icao, String regionCode, double latitude, double longitude, double altitude) {
        this.icao = icao;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public String getIcao() {
        return icao;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "icao='" + icao + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                '}';
    }
}