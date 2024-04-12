package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The cameraSetRelative6DOF function is used to adjust the user's aircraft view camera.
 */
public class CameraSetRelative6DofRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
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

    /**
     * The cameraSetRelative6DOF function is used to adjust the user's aircraft view camera.
     *
     * @param deltaX     Float containing the delta in the x-axis from the eyepoint reference point. See the [views]
     *                   section of the Aircraft Configuration Files document for a description of the eyepoint.
     * @param deltaY     Float containing the delta in the y-axis from the eyepoint reference point.
     * @param deltaZ     Float containing the delta in the z-axis from the eyepoint reference point.
     * @param pitchDeg   Float containing the pitch in degrees (rotation about the x-axis).
     *                   A postive value points the nose down, a negative value up. The range of allowable values
     *                   is +90 to -90 degrees.
     * @param bankDeg    Float containing the bank angle in degrees (rotation about the z-axis).
     *                   The range of allowable values is +180 to -180 degrees.
     * @param headingDeg Float containing the heading in degrees (rotation about the y-axis).
     *                   A positive value rotates the view right, a negative value left. If the user is viewing the 2D
     *                   cockpit, the view will change to the Virtual Cockpit 3D view if the angle exceeds 45 degrees
     *                   from the view ahead. The Virtual Cockpit view will change back to the 2D cockpit view if the
     *                   heading angle drops below 45 degrees. The range of allowable values is +180 to -180 degrees.
     */
    public CameraSetRelative6DofRequest(float deltaX, float deltaY, float deltaZ, float pitchDeg, float bankDeg,
            float headingDeg) {
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

    /**
     * Returns a float containing the delta in the x-axis from the eyepoint reference point. See the [views] section of
     * the Aircraft Configuration Files document for a description of the eyepoint.
     *
     * @return X-axis from the eyepoint reference point.
     */
    public float getDeltaX() {
        return deltaX;
    }

    /**
     * Returns a float containing the delta in the y-axis from the eyepoint reference point.
     *
     * @return Y-axis from the eyepoint reference point.
     */
    public float getDeltaY() {
        return deltaY;
    }

    /**
     * Returns a float containing the delta in the z-axis from the eyepoint reference point.
     *
     * @return Z-axis from the eyepoint reference point.
     */
    public float getDeltaZ() {
        return deltaZ;
    }

    /**
     * Returns a Float containing the pitch in degrees (rotation about the x-axis). A postive value points the nose
     * down, a negative value up. The range of allowable values is +90 to -90 degrees.
     *
     * @return Pitch in degrees.
     */
    public float getPitchDeg() {
        return pitchDeg;
    }

    /**
     * Returns a float containing the bank angle in degrees (rotation about the z-axis). The range of allowable values
     * is +180 to -180 degrees.
     *
     * @return Bank angle in degrees.
     */
    public float getBankDeg() {
        return bankDeg;
    }

    /**
     * Returns a float containing the heading in degrees (rotation about the y-axis). A positive value rotates the view
     * right, a negative value left. If the user is viewing the 2D cockpit, the view will change to the Virtual Cockpit
     * 3D view if the angle exceeds 45 degrees from the view ahead. The Virtual Cockpit view will change back to the 2D
     * cockpit view if the heading angle drops below 45 degrees. The range of allowable values is +180 to -180 degrees.
     *
     * @return Heading in degrees.
     */
    public float getHeadingDeg() {
        return headingDeg;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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