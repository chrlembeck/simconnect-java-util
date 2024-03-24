package org.lembeck.fs.copilot.proxy.request;

public enum SystemEventName {

    /**
     * Request a notification every second.
     */
    ONE_SEC("1sec"),
    FOUR_SEC("4sec"),
    SIX_HZ("6Hz"),

    AIRCRAFR_LOADED("AircraftLoaded"),
    CRASHED("Crashed"),
    CRASH_RESET("CrashReset"),
    @Deprecated
    CUSTOM_MISSION_ACTION_EXECUTED("CustomMissionActionExecuted"),
    FLOGHT_LOADED("FlightLoaded"),
    FLIGHT_SAVED("FlightSaved"),
    FLIGHT_PLAN_ACTIVATED("FlightPlanActivated"),
    FLIGHT_PLAN_DEACTIVATED("FlightPlanDeactivated"),
    FRAME("Frame"),
    OBJECT_ADDED("ObjectAdded"),
    OBJECT_REMOVED("ObjectRemoved"),
    PAUSE("Pause"),
    PAUSE_EX1("Pause_EX1"),
    PAUSED("Paused"),
    PAUSE_FRAME("PauseFrame"),
    POSITION_CHANGED("PositionChanged"),
    SIM("Sim"),
    SIM_START("SimStart"),
    SIM_STOP("SimStop"),
    SOUND("Sound"),
    UNPAUSED("Unpaused"),
    VIEW("View"),
    @Deprecated
    WEATHER_MODE_CHANGED("WeatherModeChanged");

    private final String eventName;

    SystemEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
