package org.lembeck.fs.copilot;

import java.util.ArrayList;
import java.util.List;

public class AirportInfo {

    private final String icao;

    private final double latitude;

    private final double longitude;

    private final double altitude;

    private final double distance;

    private final double heading;

    private String name;

    private final List<AirportsPanel.RunwayFacilityData> runways = new ArrayList<>(1);

    public AirportInfo(String icao, double latitude, double longitude, double altitude, double distance,
            double heading) {
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distance = distance;
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

    public double getDistance() {
        return distance;
    }

    public double getHeading() {
        return heading;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRunway(AirportsPanel.RunwayFacilityData runway) {
        this.runways.add(runway);
    }

    public List<AirportsPanel.RunwayFacilityData> getRunways() {
        return runways;
    }

    public String getName() {
        return name;
    }
}