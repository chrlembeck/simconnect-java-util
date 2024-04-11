package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_DATA_INITPOSITION structure is used to initialize the position of the user aircraft, AI controlled
 * aircraft, or other simulation object.
 */
public class InitPosition {

    /**
     * The aircraft's design cruising speed.
     */
    public static final int INITPOSITION_AIRSPEED_CRUISE = -1;

    /**
     * Maintain the current airspeed.
     */
    public static final int INITPOSITION_AIRSPEED_KEEP = -2;

    private final double latitude;

    private final double longitude;

    private final double altitude;

    private final double pitch;

    private final double bank;

    private final double heading;

    private final boolean onGround;

    private final int airspeed;

    InitPosition(ByteBuffer buffer) {
        latitude = buffer.getDouble();
        longitude = buffer.getDouble();
        altitude = buffer.getDouble();
        pitch = buffer.getDouble();
        bank = buffer.getDouble();
        heading = buffer.getDouble();
        onGround = buffer.getInt() != 0;
        airspeed = buffer.getInt();
    }

    /**
     * Creates a new init position.
     *
     * @param latitude  Latitude in degrees.
     * @param longitude Longitude in degrees.
     * @param altitude  Altitude in feet.
     * @param pitch     Pitch in degrees.
     * @param bank      Bank in degrees.
     * @param heading   Heading in degrees.
     * @param onGround  Set this to true to place the object on the ground, or false if the object is to be airborne.
     * @param airspeed  The airspeed in knots, or one of the following special values
     *                  {@link #INITPOSITION_AIRSPEED_CRUISE} or {@link #INITPOSITION_AIRSPEED_KEEP}
     */
    public InitPosition(double latitude, double longitude, double altitude, double pitch, double bank, double heading, boolean onGround, int airspeed) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.pitch = pitch;
        this.bank = bank;
        this.heading = heading;
        this.onGround = onGround;
        this.airspeed = airspeed;
    }

    void write(ByteBuffer outBuffer) {
        outBuffer.putDouble(latitude);
        outBuffer.putDouble(longitude);
        outBuffer.putDouble(altitude);
        outBuffer.putDouble(pitch);
        outBuffer.putDouble(bank);
        outBuffer.putDouble(heading);
        outBuffer.putInt(onGround ? 1 : 0);
        outBuffer.putInt(airspeed);
    }

    /**
     * Returns the latitude in degrees.
     *
     * @return Latitude in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude in degrees.
     *
     * @return Longitude in degrees.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the altitude in feet.
     *
     * @return Altitude in feet.
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Returns the pitch in degrees.
     *
     * @return Pitch in degrees.
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Returns the bank in degrees.
     *
     * @return Bank in degrees.
     */
    public double getBank() {
        return bank;
    }

    /**
     * Returns the heading in degrees.
     *
     * @return Heading in degrees.
     */
    public double getHeading() {
        return heading;
    }

    /**
     * Returns whether the object is on the ground or not.
     *
     * @return true means, the object is in the ground, false means, the object is airborne.
     */
    public boolean isOnGround() {
        return onGround;
    }

    /**
     * Returns the airspeed in knots.
     *
     * @return The airspeed in knots, or one of the following special values: {@link #INITPOSITION_AIRSPEED_KEEP} or
     * {@link #INITPOSITION_AIRSPEED_CRUISE}.
     */
    public int getAirspeed() {
        return airspeed;
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
                ", pitch=" + pitch +
                ", bank=" + bank +
                ", heading=" + heading +
                ", onGround=" + onGround +
                ", airspeed=" + airspeed +
                '}';
    }
}