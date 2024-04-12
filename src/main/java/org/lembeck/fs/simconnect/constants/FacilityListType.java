package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_FACILITY_LIST_TYPE enumeration type is used to determine which type of facilities data is being
 * requested or returned.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_FACILITY_LIST_TYPE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_FACILITY_LIST_TYPE.htm</a>
 */
public enum FacilityListType {

    /**
     * Specifies that the type of information is for an airport, see SIMCONNECT_DATA_FACILITY_AIRPORT.
     */
    AIRPORT,

    /**
     * Specifies that the type of information is for a waypoint, see SIMCONNECT_DATA_FACILITY_WAYPOINT.
     */
    WAYPOINT,

    /**
     * Specifies that the type of information is for an NDB, see SIMCONNECT_DATA_FACILITY_NDB.
     */
    NDB,

    /**
     * Specifies that the type of information is for a VOR, see SIMCONNECT_DATA_FACILITY_VOR.
     */
    VOR,

    /**
     * Not valid as a list type, but simply the number of list types.
     */
    COUNT,

    /**
     * Specifies an unknown facility lit type (should not be used).
     */
    UNKNOWN;

    /**
     * Returns the facility list type by its identifier.
     *
     * @param id Identifier of the facility list type.
     * @return Facility list type of the given identifier.
     */
    public static FacilityListType ofId(int id) {
        return switch (id) {
            case 0 -> AIRPORT;
            case 1 -> WAYPOINT;
            case 2 -> NDB;
            case 3 -> VOR;
            case 4 -> COUNT;
            default -> UNKNOWN;
        };
    }
}