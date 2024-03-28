package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class CameraSetRelative6DofRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000030;

    private final float deltaX;
    private final float deltaY;
    private final float deltaZ;
    private final float pitchDeg;
    private final float bankDeg;
    private final float headingDeg;

    CameraSetRelative6DofRequest(ByteBuffer buffer) {
        super(buffer);
        deltaX = buffer.getInt();
        deltaY = buffer.getInt();
        deltaZ = buffer.getInt();
        pitchDeg = buffer.getInt();
        bankDeg = buffer.getInt();
        headingDeg = buffer.getInt();
    }

    public CameraSetRelative6DofRequest(float deltaX, float deltaY, float deltaZ, float pitchDeg, float bankDeg, float headingDeg) {
        super(TYPE_ID);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.pitchDeg = pitchDeg;
        this.bankDeg = bankDeg;
        this.headingDeg = headingDeg;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putFloat(deltaX);
        outBuffer.putFloat(deltaY);
        outBuffer.putFloat(deltaZ);
        outBuffer.putFloat(pitchDeg);
        outBuffer.putFloat(bankDeg);
        outBuffer.putFloat(headingDeg);
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public float getDeltaZ() {
        return deltaZ;
    }

    public float getPitchDeg() {
        return pitchDeg;
    }

    public float getBankDeg() {
        return bankDeg;
    }

    public float getHeadingDeg() {
        return headingDeg;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", deltaX='" + deltaX + "'" +
                ", deltaY='" + deltaY + "'" +
                ", deltaZ='" + deltaZ + "'" +
                ", pitchDeg=" + pitchDeg +
                ", bankDeg=" + bankDeg +
                ", headingDeg=" + headingDeg +
                "}";
    }
}