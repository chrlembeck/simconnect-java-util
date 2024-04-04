package org.lembeck.fs.simconnect.constants;

public enum SystemState {

    AIRCRAFT_LOADED("AircraftLoaded"),
    DIALOG_MODE("DialogMode"),
    FLIGHT_LOADED("FlightLoaded"),
    FLIGHT_PLAN("FlightPlan"),
    SIM("Sim");

    private final String stateName;

    SystemState(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }
}