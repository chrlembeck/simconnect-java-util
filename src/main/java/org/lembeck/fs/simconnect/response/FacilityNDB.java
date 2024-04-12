package org.lembeck.fs.simconnect.response;

/**
 * The SIMCONNECT_DATA_FACILITY_NDB structure is used to return information on a single NDB station in the facilities
 * cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_NDB.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_NDB.htm</a>
 */
public class FacilityNDB extends FacilityWaypoint {

    /**
     * Frequency of the station in Hz.
     */
    protected final int frequency;

    /**
     * Creates a new NDB instance.
     *
     * @param icao       The NDB ICAO code.
     * @param regionCode The NDB region code.
     * @param latitude   Latitude of the NDB facility in degrees.
     * @param longitude  Longitude of the NDB facility in degrees.
     * @param altitude   Altitude of the NDB facility in meters.
     * @param magVar     The magnetic variation of the NDB in degrees.
     * @param frequency  Frequency of the station in Hz.
     */
    public FacilityNDB(String icao, String regionCode, double latitude, double longitude, double altitude, float magVar,
            int frequency) {
        super(icao, regionCode, latitude, longitude, altitude, magVar);
        this.frequency = frequency;
    }

    /**
     * Returns the Frequency of the station in Hz.
     *
     * @return Frequency of the station in Hz.
     */
    public int getFrequency() {
        return frequency;
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
                ", magVar=" + magVar +
                ", frequency=" + frequency +
                '}';
    }
}