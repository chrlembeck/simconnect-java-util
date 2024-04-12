package org.lembeck.fs.simconnect.response;

/**
 * The SIMCONNECT_DATA_FACILITY_AIRPORT structure is used to return information on a single airport in the facilities
 * cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_AIRPORT.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_AIRPORT.htm</a>
 */
public class FacilityAirport {

    /**
     * The airport ICAO code.
     */
    protected final String icao;

    /**
     * The airport region code.
     *
     * @see <a href="https://en.wikipedia.org/wiki/ICAO_airport_code#Prefixes">https://en.wikipedia.org/wiki/ICAO_airport_code#Prefixes</a>
     */
    protected final String regionCode;

    /**
     * Latitude of the airport facility in degrees.
     */
    protected final double latitude;

    /**
     * Longitude of the airport facility in degrees.
     */
    protected final double longitude;

    /**
     * Altitude of the facility in meters.
     */
    protected final double altitude;

    /**
     * Creates a new airport instance.
     *
     * @param icao       The airport ICAO code.
     * @param regionCode The airport region code.
     * @param latitude   Latitude of the airport facility in degrees.
     * @param longitude  Longitude of the airport facility in degrees.
     * @param altitude   Altitude of the facility in meters.
     */
    public FacilityAirport(String icao, String regionCode, double latitude, double longitude, double altitude) {
        this.icao = icao;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * Returns the airport ICAO code.
     *
     * @return The airport ICAO code.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Returns the airport region code.
     *
     * @return The airport region code.
     * @see <a href="https://en.wikipedia.org/wiki/ICAO_airport_code#Prefixes">https://en.wikipedia.org/wiki/ICAO_airport_code#Prefixes</a>
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * Returns the Latitude of the airport facility.
     *
     * @return Latitude of the airport facility in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the Longitude of the airport facility.
     *
     * @return Longitude of the airport facility in degrees.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the Altitude of the facility in meters.
     *
     * @return Altitude of the facility in meters.
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "icao='" + icao + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                '}';
    }
}