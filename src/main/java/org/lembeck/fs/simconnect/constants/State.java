package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_STATE enumeration type is used with the SimConnect_SetSystemEventState call to turn the reporting of
 * events on and off.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_STATE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_STATE.htm</a>
 */
public enum State {

    /**
     * Specifies off.
     */
    OFF,

    /**
     * Specifies on.
     */
    ON,

    /**
     * Specifies an unknown state (should not be used).
     */
    UNKNOWN;

    /**
     * Returns the state specified by the given identifier.
     *
     * @param id The identifier of the state.
     * @return State specified by the given identifier.
     */
    public static State ofId(int id) {
        return switch (id) {
            case 0 -> OFF;
            case 1 -> ON;
            default -> UNKNOWN;
        };
    }
}