package org.lembeck.fs.simconnect.response;

public class FacilityVOR extends FacilityNDB {

    protected final int flags;

    protected final float localizer;

    protected final double glideLat;

    protected final double glideLon;

    protected final double glideAlt;

    protected final float glideSlopeAngle;

    public FacilityVOR(String icao, String regionCode, double latitude, double longitude, double altitude, float magVar, int frequency, int flags, float localizer, double glideLat, double glideLon, double glideAlt, float glideSlopeAngle) {
        super(icao, regionCode, latitude, longitude, altitude, magVar, frequency);
        this.flags = flags;
        this.localizer = localizer;
        this.glideLat = glideLat;
        this.glideLon = glideLon;
        this.glideAlt = glideAlt;
        this.glideSlopeAngle = glideSlopeAngle;
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
                ", magVar=" + magVar +
                ", frequency=" + frequency +
                ", flags=" + flags +
                ", localizer=" + localizer +
                ", glideLat=" + glideLat +
                ", glideLon=" + glideLon +
                ", glideAlt=" + glideAlt +
                ", glideSlopeAngle=" + glideSlopeAngle +
                '}';
    }
}