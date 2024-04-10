package org.lembeck.fs.simconnect.constants;

/**
 * Constants for the use in the RequestSystemState function call.
 *
 * @see org.lembeck.fs.simconnect.SimConnect#requestSystemState(int, SystemState)
 */
public enum SystemState {

    /**
     * Requests the full path name of the last loaded aircraft flight dynamics file. These files have a .AIR extension.
     */
    AIRCRAFT_LOADED("AircraftLoaded"),

    /**
     * Requests whether the simulation is in Dialog mode or not.
     */
    DIALOG_MODE("DialogMode"),

    /**
     * Requests the full path name of the last loaded flight. Flight files have the extension .FLT.
     */
    FLIGHT_LOADED("FlightLoaded"),

    /**
     * Requests the full path name of the active flight plan. An empty string will be returned if there is no active flight plan.
     */
    FLIGHT_PLAN("FlightPlan"),

    /**
     * Requests the state of the simulation. If 1 is returned, the user is in control of the aircraft, if 0 is returned, the user is navigating the UI.
     * This is the same state that notifications can be subscribed to with the "SimStart" and "SimStop" string with the SimConnect_SubscribeToSystemEvent function.
     *
     * @see org.lembeck.fs.simconnect.SimConnect#subscribeToSystemEvent(int, SystemEventName)
     */
    SIM("Sim");

    private final String stateName;

    SystemState(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Returns the identifier of the system function in the format the simconnect interface is used to handle it.
     *
     * @return Identifier of the system function.
     */
    public String getStateName() {
        return stateName;
    }
}