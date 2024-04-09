package org.lembeck.fs.copilot;

public class PlanePositionEvent {

    private final double latitude;
    private final double longitude;

    private final double altitudeInFeet;
    private final double altitudeAboveGroundInFeet;
    private final double altitudeAboveGroundMinusCenterOfGravityInFeet;
    private final double verticalSpeedFeetPerSecond;
    private final double airspeedIndicated;
    private final double airspeedTrue;
    private final double bankDegrees;
    private final double pitchDegrees;
    private final double headingDegreesGyro;
    private final double headingDegreesMagnetic;
    private final double headingDegreesTrue;

    public PlanePositionEvent(double userLat, double userLon, double altitudeInFeet, double altitudeAboveGroundInFeet,
            double altitudeAboveGroundMinusCenterOfGravityInFeet, double verticalSpeedFeetPerSecond,
            double airspeedIndicated,
            double airspeedTrue, double bankDegrees, double pitchDegrees, double headingDegreesGyro,
            double headingDegreesMagnetic, double headingDegreesTrue) {
        this.latitude = userLat;
        this.longitude = userLon;
        this.altitudeInFeet = altitudeInFeet;
        this.altitudeAboveGroundInFeet = altitudeAboveGroundInFeet;
        this.altitudeAboveGroundMinusCenterOfGravityInFeet = altitudeAboveGroundMinusCenterOfGravityInFeet;
        this.bankDegrees = bankDegrees;
        this.headingDegreesGyro = headingDegreesGyro;
        this.headingDegreesMagnetic = headingDegreesMagnetic;
        this.headingDegreesTrue = headingDegreesTrue;
        this.pitchDegrees = pitchDegrees;
        this.airspeedIndicated = airspeedIndicated;
        this.airspeedTrue = airspeedTrue;
        this.verticalSpeedFeetPerSecond = verticalSpeedFeetPerSecond;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitudeInFeet() {
        return altitudeInFeet;
    }

    public double getAltitudeAboveGroundInFeet() {
        return altitudeAboveGroundInFeet;
    }

    public double getVerticalSpeedFeetPerSecond() {
        return verticalSpeedFeetPerSecond;
    }

    public double getAirspeedIndicated() {
        return airspeedIndicated;
    }

    public double getAirspeedTrue() {
        return airspeedTrue;
    }

    public double getBankDegrees() {
        return bankDegrees;
    }

    public double getPitchDegrees() {
        return pitchDegrees;
    }

    public double getHeadingDegreesGyro() {
        return headingDegreesGyro;
    }

    public double getHeadingDegreesMagnetic() {
        return headingDegreesMagnetic;
    }

    public double getHeadingDegreesTrue() {
        return headingDegreesTrue;
    }

    public double getAltitudeAboveGroundMinusCenterOfGravityInFeet() {
        return altitudeAboveGroundMinusCenterOfGravityInFeet;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "PlanePositionEvent{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitudeInFeet=" + altitudeInFeet +
                ", altitudeAboveGroundInFeet=" + altitudeAboveGroundInFeet +
                ", altitudeAboveGroundMinusCenterOfGravityInFeet=" + altitudeAboveGroundMinusCenterOfGravityInFeet +
                ", verticalSpeed=" + verticalSpeedFeetPerSecond +
                ", airspeedIndicated=" + airspeedIndicated +
                ", airspeedTrue=" + airspeedTrue +
                ", bankDegrees=" + bankDegrees +
                ", pitchDegrees=" + pitchDegrees +
                ", headingDegreesGyro=" + headingDegreesGyro +
                ", headingDegreesMagnetic=" + headingDegreesMagnetic +
                ", headingDegreesTrue=" + headingDegreesTrue +
                '}';
    }
}