package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_DATA_XYZ structure is used to hold a 3D coordinate.
 */
public class DataXYZ {

    /**
     * The position along the x-axis.
     */
    private final double x;

    /**
     * The position along the y-axis.
     */
    private final double y;

    /**
     * The position along the z-axis.
     */
    private final double z;

    DataXYZ(ByteBuffer buffer) {
        x = buffer.getDouble();
        y = buffer.getDouble();
        z = buffer.getDouble();
    }

    /**
     * Creates a new 3D coordinate object.
     *
     * @param x The position along the x-axis.
     * @param y The position along the y-axis.
     * @param z The position along the z-axis.
     */
    public DataXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns the position along the x-axis.
     *
     * @return The position along the x-axis.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the position along the y-axis.
     *
     * @return The position along the y-axis.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the position along the z-axis.
     *
     * @return The position along the z-axis.
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}