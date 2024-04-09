package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class DataXYZ {
    private final double x;
    private final double y;
    private final double z;

    DataXYZ(ByteBuffer buffer) {
        x = buffer.getDouble();
        y = buffer.getDouble();
        z = buffer.getDouble();
    }

    public DataXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}