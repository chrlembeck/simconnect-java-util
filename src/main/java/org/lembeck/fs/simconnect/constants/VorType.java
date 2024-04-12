package org.lembeck.fs.simconnect.constants;

/**
 * The VOR type as used by the AddToFacilityDefinition method.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm</a>
 */
public enum VorType {

    /**
     * Specifies an unknown VOR type.
     */
    UNKNOWN,

    /**
     * Specifies a terminal VOR type.
     */
    TERMINAL,

    /**
     * Specifies a low altitude VOR type.
     */
    LOW_ALTITUDE,

    /**
     * Specifies a low alt VOR type.
     */
    LOW_ALT,

    /**
     * Specifies a high altitude VOR type.
     */
    HIGH_ALTITUDE,

    /**
     * Specifies a high alt VOR type.
     */
    HIGH_ALT,

    /**
     * Specifies an ILS VOR type.
     */
    ILS,

    /**
     * Specifies a VOR test facility station.
     */
    VOT;

    /**
     * Returns the VOR type specified by its identifier.
     *
     * @param id Identifier of the VOR type.
     * @return The VOR type specified by its identifier.
     */
    public static VorType ofId(int id) {
        return switch (id) {
            case 0 -> UNKNOWN;
            case 1 -> TERMINAL;
            case 2 -> LOW_ALTITUDE;
            case 3 -> LOW_ALT;
            case 4 -> HIGH_ALTITUDE;
            case 5 -> HIGH_ALT;
            case 6 -> ILS;
            case 7 -> VOT;
            default -> UNKNOWN;
        };
    }
}