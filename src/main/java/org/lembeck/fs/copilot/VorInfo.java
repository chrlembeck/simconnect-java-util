package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.constants.VorType;

public class VorInfo {

    private final String regionCode;
    private String icao;

    private final double latitude;

    private final double longitude;

    private final double altitude;

    private final double distanceNM;

    private final double heading;

    private String name;

    private boolean nav;
    private boolean dme;
    private boolean tacan;

    private int frequency;

    private VorType type;

    private float rangeMeters;

    private float magVar;

    public VorInfo(String icao, String regionCode, double latitude, double longitude, double altitude, double distanceNM, double heading) {
        this.icao = icao;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distanceNM = distanceNM;
        this.heading = heading;
    }

    public String getIcao() {
        return icao;
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

    public double getDistanceNM() {
        return distanceNM;
    }

    public double getHeading() {
        return heading;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNav() {
        return nav;
    }

    public boolean isDme() {
        return dme;
    }

    public boolean isTacan() {
        return tacan;
    }

    public int getFrequency() {
        return frequency;
    }

    public VorType getType() {
        return type;
    }

    public float getRangeMeters() {
        return rangeMeters;
    }

    public float getMagVar() {
        return magVar;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public void setNav(boolean nav) {
        this.nav = nav;
    }

    public void setDme(boolean dme) {
        this.dme = dme;
    }

    public void setTacan(boolean tacan) {
        this.tacan = tacan;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setType(VorType type) {
        this.type = type;
    }

    public void setRangeMeters(float rangeMeters) {
        this.rangeMeters = rangeMeters;
    }

    public void setMagVar(float magVar) {
        this.magVar = magVar;
    }

    public String getFrequencyString() {
        String txt = Integer.toString(frequency);
        return txt.length() > 4 ? txt.substring(0, 3) + "." + txt.substring(3, 5) : "";
    }
}