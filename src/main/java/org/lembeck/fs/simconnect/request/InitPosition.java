package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

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

    public void write(ByteBuffer outBuffer) {
        outBuffer.putDouble(latitude);
        outBuffer.putDouble(longitude);
        outBuffer.putDouble(altitude);
        outBuffer.putDouble(pitch);
        outBuffer.putDouble(bank);
        outBuffer.putDouble(heading);
        outBuffer.putInt(onGround ? 1 : 0);
        outBuffer.putInt(airspeed);
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

    public double getPitch() {
        return pitch;
    }

    public double getBank() {
        return bank;
    }

    public double getHeading() {
        return heading;
    }

    public boolean isOnGround() {
        return onGround;
    }

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