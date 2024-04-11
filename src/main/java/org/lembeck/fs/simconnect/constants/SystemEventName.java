package org.lembeck.fs.simconnect.constants;

/**
 * Enumeration of the available system event names used by the SubscribeToSystemEvent request.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
 */
public enum SystemEventName {

    /**
     * Request a notification every second.
     */
    ONE_SEC("1sec"),

    /**
     * Request a notification every four seconds.
     */
    FOUR_SEC("4sec"),

    /**
     * Request notifications six times per second. This is the same rate that joystick movement events are transmitted.
     */
    SIX_HZ("6Hz"),

    /**
     * Request a notification when the aircraft flight dynamics file is changed. These files have a .AIR extension.
     * The filename is returned in a SIMCONNECT_RECV_EVENT_FILENAME structure.
     */
    AIRCRAFR_LOADED("AircraftLoaded"),

    /**
     * Request a notification if the user aircraft crashes.
     */
    CRASHED("Crashed"),

    /**
     * Request a notification when the crash cut-scene has completed.
     */
    CRASH_RESET("CrashReset"),

    /**
     * Request a notification when a mission action has been executed.
     */
    @Deprecated
    CUSTOM_MISSION_ACTION_EXECUTED("CustomMissionActionExecuted"),

    /**
     * Request a notification when a flight is loaded. Note that when a flight is ended, a default flight is typically
     * loaded, so these events will occur when flights and missions are started and finished. The filename of the flight
     * loaded is returned in a SIMCONNECT_RECV_EVENT_FILENAME structure.
     */
    FLIGHT_LOADED("FlightLoaded"),

    /**
     * Request a notification when a flight is saved correctly. The filename of the flight saved is returned in a
     * SIMCONNECT_RECV_EVENT_FILENAME structure.
     */
    FLIGHT_SAVED("FlightSaved"),

    /**
     * Request a notification when a new flight plan is activated. The filename of the activated flight plan is returned
     * in a SIMCONNECT_RECV_EVENT_FILENAME structure.
     */
    FLIGHT_PLAN_ACTIVATED("FlightPlanActivated"),

    /**
     * Request a notification when the active flight plan is de-activated.
     */
    FLIGHT_PLAN_DEACTIVATED("FlightPlanDeactivated"),

    /**
     * Request notifications every visual frame. Information is returned in a SIMCONNECT_RECV_EVENT structure.
     */
    FRAME("Frame"),

    /**
     * Request a notification when an AI object is added to the simulation. Refer also to the
     * SIMCONNECT_RECV_EVENT_OBJECT_ADDREMOVE structure.
     */
    OBJECT_ADDED("ObjectAdded"),

    /**
     * Request a notification when an AI object is removed from the simulation. Refer also to the
     * SIMCONNECT_RECV_EVENT_OBJECT_ADDREMOVE structure.
     */
    OBJECT_REMOVED("ObjectRemoved"),

    /**
     * Request notifications when the flight is paused or unpaused, and also immediately returns the current pause
     * state (1 = paused or 0 = unpaused). The state is returned in the dwData parameter.
     */
    PAUSE("Pause"),

    /**
     * Request notifications when the flight is paused or unpaused, and also immediately returns the current pause state
     * with more detail than the regular Pause system event. The state is returned in the dwData parameter, and will be
     * one of the following defines:
     * <ul>
     * <li>0: No Pause</li>
     * <li>1: "full" Pause (sim + traffic + etc...)</li>
     * <li>2: FSX Legacy Pause (not used anymore)</li>
     * <li>4: Pause was activated using the "Active Pause" Button</li>
     * <li>8: Pause the player sim but traffic, multi, etc... will still run</li>
     * </ul>
     */
    PAUSE_EX1("Pause_EX1"),

    /**
     * Request a notification when the flight is paused.
     */
    PAUSED("Paused"),

    /**
     * Request notifications for every visual frame that the simulation is paused. Information is returned in a
     * SIMCONNECT_RECV_EVENT structure.
     */
    PAUSE_FRAME("PauseFrame"),

    /**
     * Request a notification when the user changes the position of their aircraft through a dialog.
     */
    POSITION_CHANGED("PositionChanged"),

    /**
     * Request notifications when the flight is running or not, and also immediately returns the current state
     * (1 = running or 0 = not running). The state is returned in the dwData parameter.
     */
    SIM("Sim"),

    /**
     * The simulator is running. Typically the user is actively controlling the aircraft on the ground or in the air.
     * However, in some cases additional pairs of SimStart/SimStop events are sent. For example, when a flight is reset
     * the events that are sent are SimStop, SimStart, SimStop, SimStart. Also when a flight is started with the
     * SHOW_OPENING_SCREEN value set to zero, then an additional SimStart/SimStop pair are sent before a second SimStart
     * event is sent when the scenery is fully loaded. The opening screen provides the options to change aircraft,
     * departure airport, and so on.
     */
    SIM_START("SimStart"),

    /**
     * The simulator is not running. Typically the user is loading a flight, navigating the shell or in a dialog.
     */
    SIM_STOP("SimStop"),

    /**
     * Requests a notification when the master sound switch is changed. This request will also return the current state
     * of the master sound switch immediately. A flag is returned in the dwData parameter, 0 if the switch is off,
     * SIMCONNECT_SOUND_SYSTEM_EVENT_DATA_MASTER (0x1) if the switch is on.
     */
    SOUND("Sound"),

    /**
     * Request a notification when the flight is un-paused.
     */
    UNPAUSED("Unpaused"),

    /**
     * Requests a notification when the user aircraft view is changed. This request will also return the current view
     * immediately. A flag is returned in the dwData parameter, one of:
     * <ul>
     * <li>SIMCONNECT_VIEW_SYSTEM_EVENT_DATA_COCKPIT_2D</li>
     * <li>SIMCONNECT_VIEW_SYSTEM_EVENT_DATA_COCKPIT_VIRTUAL</li>
     * <li>SIMCONNECT_VIEW_SYSTEM_EVENT_DATA_ORTHOGONAL (the map view).</li>
     * </ul>
     */
    VIEW("View"),

    /**
     * Request a notification when the weather mode is changed.
     */
    @Deprecated
    WEATHER_MODE_CHANGED("WeatherModeChanged");

    private final String eventName;

    SystemEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns the name of the event.
     *
     * @return Name of the event.
     */
    public String getEventName() {
        return eventName;
    }
}