package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_DATA_PBH structure is used to hold a world orientation.
 */
public class PitchBankHeading {

    /**
     * The pitch in degrees.
     */
    private final float pitch;

    /**
     * The bank in degrees.
     */
    private final float bank;

    /**
     * The heading in degrees.
     */
    private final float heading;

    PitchBankHeading(ByteBuffer buffer) {
        pitch = buffer.getFloat();
        bank = buffer.getFloat();
        heading = buffer.getFloat();
    }

    /**
     * Creates a new world orientation data object.
     *
     * @param pitch   The pitch in degrees.
     * @param bank    The bank in degrees.
     * @param heading The heading in degrees.
     */
    public PitchBankHeading(float pitch, float bank, float heading) {
        this.pitch = pitch;
        this.bank = bank;
        this.heading = heading;
    }

    /**
     * Returns the pitch in degrees.
     *
     * @return The pitch in degrees.
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Returns the bank in degrees.
     *
     * @return The bank in degrees.
     */
    public float getBank() {
        return bank;
    }

    /**
     * Returns the heading in degrees.
     *
     * @return The heading in degrees.
     */
    public float getHeading() {
        return heading;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "pitch=" + pitch +
                ", bank=" + bank +
                ", heading=" + heading +
                '}';
    }
}