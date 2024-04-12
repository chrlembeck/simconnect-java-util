package org.lembeck.fs.simconnect.response;

/**
 * The SIMCONNECT_DATA_FACILITY_WAYPOINT structure used to return information on a single waypoint in the facilities
 * cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_WAYPOINT.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_WAYPOINT.htm</a>
 */
public class FacilityWaypoint extends FacilityAirport {

    /**
     * The magnetic variation of the waypoint in degrees.
     */
    protected final float magVar;

    /**
     * Creates a new waypoint instance.
     *
     * @param icao       The waypoint ICAO code.
     * @param regionCode The waypoint region code.
     * @param latitude   Latitude of the waypoint facility in degrees.
     * @param longitude  Longitude of the waypoint facility in degrees.
     * @param altitude   Altitude of the waypoint facility in meters.
     * @param magVar     The magnetic variation of the waypoint in degrees.
     */
    public FacilityWaypoint(String icao, String regionCode, double latitude, double longitude, double altitude,
            float magVar) {
        super(icao, regionCode, latitude, longitude, altitude);
        this.magVar = magVar;
    }

    /**
     * Returns the magnetic variation of the waypoint in degrees.
     *
     * @return The magnetic variation of the waypoint in degrees.
     */
    public float getMagVar() {
        return magVar;
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
                '}';
    }
}