package org.lembeck.fs.simconnect.constants;

/**
 * Expected data types in the SIMCONNECT_INPUT_EVENT_DESCRIPTOR structure.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_INPUT_EVENT_DESCRIPTOR.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_INPUT_EVENT_DESCRIPTOR.htm</a>
 */
public enum InputEventType {

    /**
     * Type double.
     */
    DOUBLE,

    /**
     * Type String.
     */
    STRING,

    /**
     * Unknown type (should not be used).
     */
    UNKNOWN;

    /**
     * Returns the type specified by its identifier.
     *
     * @param id Identifier of the type.
     * @return The type specified by its identifier.
     */
    public static InputEventType ofId(int id) {
        return switch (id) {
            case 0 -> DOUBLE;
            case 1 -> STRING;
            default -> UNKNOWN;
        };
    }
}