package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_SIMOBJECT_TYPE enumeration type is used with the SimConnect_RequestDataOnSimObjectType call to request
 * information on specific or nearby objects.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_SIMOBJECT_TYPE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_SIMOBJECT_TYPE.htm</a>
 */
public enum SimObjectType {

    /**
     * Specifies the user's aircraft.
     */
    USER("User"),

    /**
     * Specifies all AI controlled objects.
     */
    ALL("All"),

    /**
     * Specifies all aircraft.
     */
    AIRCRAFT("Airplane"),

    /**
     * Specifies all helicopters.
     */
    HELICOPTER("Helicopter"),

    /**
     * Specifies all AI controlled boats.
     */
    BOAT("Boat"),

    /**
     * Specifies all AI controlled ground vehicles.
     */
    GROUND("GroundVehicle"),

    /**
     * Specifies an unknown sim object type (should not be used).
     */
    UNKNOWN("UNKNOWN");

    private final String identifier;

    SimObjectType(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the sim object type specified by its string identifier.
     *
     * @param identifier String identifier of the sim object type.
     * @return The sim object type specified by its string identifier.
     */
    public static SimObjectType fromIdentifier(String identifier) {
        for (SimObjectType type : values()) {
            if (type.identifier.equalsIgnoreCase(identifier)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    /**
     * Returns the sim object type specified by its identifier.
     *
     * @param id Identifier of the sim object type.
     * @return The sim object type specified by its identifier.
     */
    public static SimObjectType ofId(int id) {
        return switch (id) {
            case 0 -> USER;
            case 1 -> ALL;
            case 2 -> AIRCRAFT;
            case 3 -> HELICOPTER;
            case 4 -> BOAT;
            case 5 -> GROUND;
            default -> UNKNOWN;
        };
    }
}