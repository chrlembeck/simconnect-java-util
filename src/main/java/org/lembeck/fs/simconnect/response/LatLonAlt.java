package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_DATA_LATLONALT structure is used to hold a world position.
 */
public class LatLonAlt {

    /**
     * Latitude in degrees.
     */
    private final double latitude;

    /**
     * Longitude in degrees.
     */
    private final double longitude;

    /**
     * Altitude in feet.
     */
    private final double altitude;

    LatLonAlt(ByteBuffer buffer) {
        latitude = buffer.getDouble();
        longitude = buffer.getDouble();
        altitude = buffer.getDouble();
    }

    /**
     * Creates a new world position coordinates object.
     *
     * @param latitude  Latitude in degrees.
     * @param longitude Longitude in degrees.
     * @param altitude  Altitude in feet.
     */
    public LatLonAlt(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * Returns the latitude in degrees.
     *
     * @return The latitude in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude in degrees.
     *
     * @return The longitude in degrees.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the altitude in feet.
     *
     * @return The altitude in feet.
     */
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