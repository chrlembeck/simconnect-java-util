package org.lembeck.fs.copilot;

public class Point2D implements Cloneable {
    public double x;
    public double y;

    /**
     * @param x
     * @param y
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        this(0.0, 0.0);
    }

    public Point2D(Point2D pixel) {
        this.x  = pixel.x;
        this.y = pixel.y;
    }



    public void scale(double s1, double s2) {
        x *= s1;
        y *= s2;
    }

    public void scale(double s) {
        scale(s,s);
    }

    public void translate(double t1, double t2) {
        x += t1;
        y += t2;
    }

    public void translate(Point2D p) {
        translate(p.x, p.y);
    }

    /**
     * Rotate this point around a specified center
     * @param xCenter x position of the center
     * @param yCenter y position of the center
     * @param angleRadians
     */
    public void rotate(double xCenter, double yCenter, double angleRadians) {
        double nx = (x - xCenter) * Math.cos(angleRadians) - (y - yCenter) * Math.sin(angleRadians);
        double ny = (x - xCenter) * Math.sin(angleRadians) + (y - yCenter) * Math.cos(angleRadians);
        this.x = nx + xCenter;
        this.y = ny + yCenter;
    }

    public void rotate(Point2D pCenter, double angleRadians) {
        rotate(pCenter.x, pCenter.y, angleRadians);
    }

    public void rotate(double angleRadians) {
        rotate(0, 0, angleRadians);
    }

    /**
     * Returns the euclidean norm of the vector (0,0) -> (x, y)
     * @return
     */
    public double dist() {
        return Math.hypot(x, y);
    }

    public double distFrom(double dx, double dy) {
        return Math.hypot(x-dx, y-dy);
    }

    public double distFrom(Point2D op) {
        return distFrom(op.x, op.y);
    }

    /**
     * returns the manhattan norm of the vector (0,0) ->  (x,y)
     * @return
     */
    public double norm1() {
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * Returns the euclidean norm of the vector (0,0) -> (x, y)
     * @return
     */
    public double norm2() {
        return dist();
    }

    /**
     * Returns the infinite norm of the vector (0,0) -> (x, y)
     * @return
     */
    public double normInf() {
        return Math.max(x, y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point2D p2d) {
        set(p2d.x, p2d.y);
    }

    public void setFromDegrees(double latDegrees, double lonDegrees) {
        set(Math.toRadians(latDegrees), Math.toRadians(lonDegrees));
    }

    public void setFromRadians(double latRadians, double lonRadians) {
        set(latRadians, lonRadians);
    }

    public void fromDegrees(double xDeg, double yDeg) {
        set(Math.toRadians(xDeg), Math.toRadians(yDeg));
    }

    public void swap() {
        double temp = x;
        x = y;
        y = temp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point2D) {
            Point2D pp = (Point2D) obj;
            return pp.x == x && pp.y == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        long b1 = Double.doubleToLongBits(x);
        long b2 = Double.doubleToLongBits(y);
        return (int) ( b1 ^ (b1 >>> 32) ^ b2 ^ (b2 >>> 32));
    }

    @Override
    public Point2D clone() {
        return new Point2D(this);
    }

    @Override
    public String toString() {
        return x + "," + y; //$NON-NLS-1$
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point2D toDegrees() {
        return new Point2D(Math.toDegrees(x), Math.toDegrees(y));
    }

    public Point2D toRadians() {
        return new Point2D(Math.toRadians(x), Math.toRadians(y));
    }


}