package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class LatLonAlt {

    private final double latitude;
    private final double longitude;
    private final double altitude;

    LatLonAlt(ByteBuffer buffer) {
        latitude = buffer.getDouble();
        longitude = buffer.getDouble();
        altitude = buffer.getDouble();
    }

    public LatLonAlt(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
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
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                '}';
    }
}