package org.lembeck.fs.simconnect.constants;

/**
 * Describes the type of frequencies as child members of the facility AIRPORT entry point.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm#frequency">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm#frequency</a>
 */
public enum FrequencyType {

    /**
     * No type for this frequency.
     */
    NONE,

    /**
     * ATIS frequency.
     */
    ATIS,

    /**
     * Multicom frequency.
     */
    MULTICOM,

    /**
     * Unicom frequency
     */
    UNICOM,

    /**
     * Common traffic advisory frequency
     */
    CTAF,

    /**
     * Ground frequency.
     */
    GROUND,

    /**
     * Tower frequency.
     */
    TOWER,

    /**
     * Clearance delivery frequency.
     */
    CLEARANCE_DELIVERY,

    /**
     * Approach frequency.
     */
    APPROACH,

    /**
     * Departure frequency.
     */
    DEPARTURE,

    /**
     * Center frequency.
     */
    CENTER,

    /**
     * Flight service station frequency.
     */
    FSS,

    /**
     * Automated weather observing system frequency.
     */
    AWOS,

    /**
     * Automated surface observing system frequency.
     */
    ASOS,

    /**
     * Pre taxi clearance frequency.
     */
    CLEARANCE_PRE_TAXI,

    /**
     * Remote clearance delivery frequency.
     */
    REMOTE_CLEARANCE_DELIVERY,

    /**
     * Unknown frequency type (do not use).
     */
    UNKOWN;

    /**
     * Returns the type specified by its identifier.
     *
     * @param id Identifier of the type.
     * @return The type specified by its identifier.
     */
    public static FrequencyType ofId(int id) {
        return switch (id) {
            case 0 -> NONE;
            case 1 -> ATIS;
            case 2 -> MULTICOM;
            case 3 -> UNICOM;
            case 4 -> CTAF;
            case 5 -> GROUND;
            case 6 -> TOWER;
            case 7 -> CLEARANCE_DELIVERY;
            case 8 -> APPROACH;
            case 9 -> DEPARTURE;
            case 10 -> CENTER;
            case 11 -> FSS;
            case 12 -> AWOS;
            case 13 -> ASOS;
            case 14 -> CLEARANCE_PRE_TAXI;
            case 15 -> REMOTE_CLEARANCE_DELIVERY;
            default -> UNKOWN;
        };
    }
}
