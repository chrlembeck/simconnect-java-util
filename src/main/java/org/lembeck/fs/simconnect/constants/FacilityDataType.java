package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_FACILITY_DATA_TYPE enumeration type is used within the SIMCONNECT_RECV_FACILITY_DATA return to give
 * the type of data that is being received.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_FACILITY_DATA_TYPE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_FACILITY_DATA_TYPE.htm</a>
 */
public enum FacilityDataType {

    /**
     * Contents of the parent struct are for an airport. See AIRPORT for more information.
     */
    AIRPORT,

    /**
     * Contents of the parent struct are for a runway. See RUNWAY for more information.
     */
    RUNWAY,

    /**
     * Contents of the parent struct are for defining an airport start position. See START for more information.
     */
    START,

    /**
     * Contents of the parent struct are for frequencies. See FREQUENCY for more information.
     */
    FREQUENCY,

    /**
     * Contents of the parent struct are for a helipad. See HELIPAD for more information.
     */
    HELIPAD,

    /**
     * Contents of the parent struct are for an approach. See APPROACH for more information.
     */
    APPROACH,

    /**
     * Contents of the parent struct are for an approach transition. See APPROACH_TRANSITION for more information.
     */
    APPROACH_TRANSITION,

    /**
     * Contents of the parent struct are for an approach leg. See APPROACH_LEG for more information.
     */
    APPROACH_LEG,

    /**
     * Contents of the parent struct are for a final approach leg. See FINAL_APPROACH_LEG for more information.
     */
    FINAL_APPROACH_LEG,

    /**
     * Contents of the parent struct are for a missed approach leg. See MISSED_APPROACH_LEG for more information.
     */
    MISSED_APPROACH_LEG,

    /**
     * Contents of the parent struct are for a departure. See DEPARTURE for more information.
     */
    DEPARTURE,

    /**
     * Contents of the parent struct are for an arrival. See ARRIVAL for more information.
     */
    ARRIVAL,

    /**
     * Contents of the parent struct are for a runway transition. See RUNWAY_TRANSITION for more information.
     */
    RUNWAY_TRANSITION,

    /**
     * Contents of the parent struct are for a route transition. See ENROUTE_TRANSITION for more information.
     */
    ENROUTE_TRANSITION,

    /**
     * Contents of the parent struct are for a taxiway point. See TAXI_POINT for more information.
     */
    TAXI_POINT,

    /**
     * Contents of the parent struct are for a taxiway parking spot. See TAXI_PARKING for more information.
     */
    TAXI_PARKING,

    /**
     * Contents of the parent struct are for a taxiway path. See TAXI_PATH for more information.
     */
    TAXI_PATH,

    /**
     * Contents of the parent struct are for a taxi name. See TAXI_NAME for more information.
     */
    TAXI_NAME,

    /**
     * Contents of the parent struct are for a jetway. See JETWAY for more information.
     */
    JETWAY,

    /**
     * Contents of the parent struct are for a VOR station. See VOR for more information.
     */
    VOR,

    /**
     * Contents of the parent struct are for an NDB station. See NDB for more information.
     */
    NDB,

    /**
     * Contents of the parent struct are for a waypoint. See WAYPOINT for more information.
     */
    WAYPOINT,

    /**
     * Contents of the parent struct are for a route. See ROUTE for more information.
     */
    ROUTE,

    /**
     * Contents of the parent struct are for a pavement element. See PAVEMENT for more information.
     */
    PAVEMENT,

    /**
     * Contents of the parent struct are for the runway approach lights. See APPROACHLIGHTS for more information.
     */
    APPROACH_LIGHTS,

    /**
     * Contents of the parent struct are for VASI information. See VASI for more information.
     */
    VASI,

    /**
     * Specifies an unknown facility data type (should not be used).
     */
    UNKNOWN;

    /**
     * Returns the facility data type specified by the given identifier.
     *
     * @param id Identifier of the facility data type.
     * @return The facility data type specified by the given identifier.
     */
    public static FacilityDataType ofId(int id) {
        return switch (id) {
            case 0 -> AIRPORT;
            case 1 -> RUNWAY;
            case 2 -> START;
            case 3 -> FREQUENCY;
            case 4 -> HELIPAD;
            case 5 -> APPROACH;
            case 6 -> APPROACH_TRANSITION;
            case 7 -> APPROACH_LEG;
            case 8 -> FINAL_APPROACH_LEG;
            case 9 -> MISSED_APPROACH_LEG;
            case 10 -> DEPARTURE;
            case 11 -> ARRIVAL;
            case 12 -> RUNWAY_TRANSITION;
            case 13 -> ENROUTE_TRANSITION;
            case 14 -> TAXI_POINT;
            case 15 -> TAXI_PARKING;
            case 16 -> TAXI_PATH;
            case 17 -> TAXI_NAME;
            case 18 -> JETWAY;
            case 19 -> VOR;
            case 20 -> NDB;
            case 21 -> WAYPOINT;
            case 22 -> ROUTE;
            case 23 -> PAVEMENT;
            case 24 -> APPROACH_LIGHTS;
            case 25 -> VASI;
            default -> UNKNOWN;
        };
    }
}