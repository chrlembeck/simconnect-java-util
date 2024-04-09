package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class PitchBankHeading {

    private final float pitch;
    private final float bank;
    private final float heading;

    PitchBankHeading(ByteBuffer buffer) {
        pitch = buffer.getFloat();
        bank = buffer.getFloat();
        heading = buffer.getFloat();
    }

    public PitchBankHeading(float pitch, float bank, float heading) {
        this.pitch = pitch;
        this.bank = bank;
        this.heading = heading;
    }

    public float getPitch() {
        return pitch;
    }

    public float getBank() {
        return bank;
    }

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