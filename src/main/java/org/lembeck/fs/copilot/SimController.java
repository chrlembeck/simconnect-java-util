package org.lembeck.fs.copilot;

import flightsim.simconnect.*;
import flightsim.simconnect.recv.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static flightsim.simconnect.SimConnectConstants.OBJECT_ID_USER;

public class SimController implements OpenHandler, ExceptionHandler, EventHandler, FacilitiesListHandler, SimObjectDataHandler {

    private SimConnect sc;
    private DispatcherTask dt;

    boolean simulationRunning = false;

    private final int CLIENT_EVENT_ID_SIM = 1;
    private final int CLIENT_EVENT_ID_4_SEC = 2;
    private final int CLIENT_EVENT_ID_AIRPORT_LIST = 3;

    private final int CLIENT_EVENT_ID_EVENT_TEST = 4;

    private final int CLIENT_EVENT_ID_AP_MASTER = 5;
    private final int CLIENT_EVENT_ID_AP_ALT_HOLD = 6;
    private final int CLIENT_EVENT_ID_AP_APR_HOLD = 7;
    private final int CLIENT_EVENT_ID_AP_HDG_HOLD = 8;
    private final int CLIENT_EVENT_ID_AP_NAV1_HOLD = 9;
    private final int CLIENT_EVENT_ID_AP_VS_HOLD = 10;
    private final int CLIENT_EVENT_ID_HEADING_BUG_SET = 11;
    private final int CLIENT_EVENT_ID_LANDING_LIGHTS_TOGGLE = 12;
    private final int CLIENT_EVENT_ID_TOGGLE_RECOGNITION_LIGHTS = 13;
    private final int CLIENT_EVENT_ID_TOGGLE_NAV_LIGHTS = 14;
    private final int CLIENT_EVENT_ID_TOGGLE_AVIONICS_MASTER = 15;
    private final int CLIENT_EVENT_ID_PITOT_HEAT_TOGGLE = 16;
    private final int CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY = 17;
    private final int CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY_ALTERNATOR_1 = 18;
    private final int CLIENT_EVENT_ID_FUEL_PUMP_TOGGLE = 19;
    private final int CLIENT_EVENT_ID_STROBES_TOGGLE = 20;
    private final int CLIENT_EVENT_ID_STROBES_OFF = 21;
    private final int CLIENT_EVENT_ID_STROBES_ON = 22;
    private final int CLIENT_EVENT_ID_TOGGLE_BEACON_LIGHTS = 23;
    private final int CLIENT_EVENT_ID_CABIN_LIGHTS_SET = 24;
    private final int CLIENT_EVENT_ID_PARKING_BRAKES = 25;
    private final int CLIENT_EVENT_ID_AP_VS_VAR_SET_ENGLISH = 26;

    private final int DATA_REQUEST_ID_PLANE_POSITION = 1;
    private final int DATA_REQUEST_ID_AUTOPILOT_STATE = 2;
    private final int DATA_REQUEST_ID_SWITCHES = 3;

    private final int DATA_DEFINITION_ID_PLANE_POSITION = 1;
    private final int DATA_DEFINITION_ID_AUTOPILOT_STATE = 2;
    private final int DATA_DEFINITION_ID_SWITCHES = 3;

    private List<SimListener> listeners = new ArrayList<>();

    public SimController() {
        try {
            //sc = new SimConnect("NearestAirports", "192.168.0.170", 26011);
            sc = new SimConnect("NearestAirports", "localhost", 26010);

            sc.subscribeToSystemEvent(CLIENT_EVENT_ID_SIM, "Sim");
            sc.subscribeToSystemEvent(CLIENT_EVENT_ID_4_SEC, "4sec");


            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE LATITUDE", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE LONGITUDE", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALTITUDE", "FEET", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALT ABOVE GROUND", "FEET", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALT ABOVE GROUND MINUS CG", "FEET", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "VERTICAL SPEED", "FEET PER SECOND", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "AIRSPEED INDICATED", "KNOTS", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "AIRSPEED TRUE", "KNOTS", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE BANK DEGREES", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE PITCH DEGREES", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES GYRO", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES MAGNETIC", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES TRUE", "DEGREES", SimConnectDataType.FLOAT64);
            sc.requestDataOnSimObject(DATA_REQUEST_ID_PLANE_POSITION, DATA_DEFINITION_ID_PLANE_POSITION, OBJECT_ID_USER, SimConnectPeriod.SIM_FRAME);

            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT MASTER", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT HEADING LOCK", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT HEADING LOCK DIR", "DEGREES", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT NAV1 LOCK", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT VERTICAL HOLD", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT VERTICAL HOLD VAR", "feet/minute", SimConnectDataType.FLOAT64);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT ALTITUDE LOCK", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT ALTITUDE LOCK VAR", "FEET", SimConnectDataType.FLOAT64);
            sc.requestDataOnSimObject(DATA_REQUEST_ID_AUTOPILOT_STATE, DATA_DEFINITION_ID_AUTOPILOT_STATE, OBJECT_ID_USER, SimConnectPeriod.SIM_FRAME, SimConnectConstants.DATA_REQUEST_FLAG_CHANGED, 0, 0, 0);

            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "ELECTRICAL MASTER BATTERY", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "GENERAL ENG MASTER ALTERNATOR:1", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "GENERAL ENG FUEL PUMP SWITCH:1", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT LANDING", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT NAV", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT CABIN", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT STROBE", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT BEACON", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT TAXI", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT RECOGNITION", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "PITOT HEAT SWITCH:1", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "AVIONICS MASTER SWITCH:1", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "BRAKE PARKING INDICATOR", "BOOL", SimConnectDataType.INT32);
            sc.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "BRAKE PARKING POSITION", "BOOL", SimConnectDataType.INT32);
            sc.requestDataOnSimObject(DATA_REQUEST_ID_SWITCHES, DATA_DEFINITION_ID_SWITCHES, OBJECT_ID_USER, SimConnectPeriod.SIM_FRAME, SimConnectConstants.DATA_REQUEST_FLAG_CHANGED, 0, 0, 0);

            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_MASTER, "AP_MASTER");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_ALT_HOLD, "AP_ALT_HOLD");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_APR_HOLD, "AP_APR_HOLD");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_HDG_HOLD, "AP_HDG_HOLD");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_NAV1_HOLD, "AP_NAV1_HOLD");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_VS_HOLD, "AP_VS_HOLD");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_HEADING_BUG_SET, "HEADING_BUG_SET");

            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_LANDING_LIGHTS_TOGGLE, "LANDING_LIGHTS_TOGGLE");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_RECOGNITION_LIGHTS, "TOGGLE_RECOGNITION_LIGHTS");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_NAV_LIGHTS, "TOGGLE_NAV_LIGHTS");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_TOGGLE, "STROBES_TOGGLE");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_ON, "STROBES_ON");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_OFF, "STROBES_OFF");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_BEACON_LIGHTS, "TOGGLE_BEACON_LIGHTS");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_AVIONICS_MASTER, "TOGGLE_AVIONICS_MASTER");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_PITOT_HEAT_TOGGLE, "PITOT_HEAT_TOGGLE");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY, "TOGGLE_MASTER_BATTERY");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY_ALTERNATOR_1, "TOGGLE_ALTERNATOR1");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_FUEL_PUMP_TOGGLE, "FUEL_PUMP");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_CABIN_LIGHTS_SET, "CABIN_LIGHTS_SET");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_PARKING_BRAKES, "PARKING_BRAKES");
            sc.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_VS_VAR_SET_ENGLISH, "AP_VS_VAR_SET_ENGLISH");


            dt = new DispatcherTask(sc);
            dt.addOpenHandler(this);
            dt.addExceptionHandler(this);
            dt.addEventHandler(this);
            dt.addFacilitiesListHandler(this);
            dt.addSimObjectDataHandler(this);

            addListener(new LoggingSimListener());
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
    }

    public void cabineLightsSet(int state, int index) {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_CABIN_LIGHTS_SET, (state & 0xFFFF) | ((index & 0xffff) << 16));
    }

    public void setVerticalSpeedAutopilotVar(int vsFeetPerMinute, int index) {
//        int data = (vsFeetPerMinute & 0xFFFF) | ((index & 0xffff) << 16);
//        System.out.println("sending " + data + " " + Integer.toBinaryString(data));
        sendEventEx(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_VS_VAR_SET_ENGLISH, vsFeetPerMinute,index,0,0,0);
    }

    public void toggleAutopilotMaster() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_MASTER, 0);
    }

    public void toggleParkingBrakes() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_PARKING_BRAKES, 0);
    }

    public void toggleAutopilotAltitudeHold() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_ALT_HOLD, 0);
    }

    public void toggleLandingLights() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_LANDING_LIGHTS_TOGGLE, 0);
    }

    public void toggleRecognitionLights() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_RECOGNITION_LIGHTS, 0);
    }

    public void toggleBeaconLights() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_BEACON_LIGHTS, 0);
    }

    public void toggleStrobeLights() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_STROBES_TOGGLE, 0);
    }

    public void strobesOn() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_STROBES_ON, 0);
    }

    public void strobesOff() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_STROBES_OFF, 0);
    }

    public void toggleNavLights() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_NAV_LIGHTS, 0);
    }

    public void toggleAvionicsMaster() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_AVIONICS_MASTER, 0);
    }

    public void togglePitotHeat() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_PITOT_HEAT_TOGGLE, 0);
    }

    public void toggleMasterBattery() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY, 0);
    }

    public void toggleMasterBatteryAlternator() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY_ALTERNATOR_1, 0);
    }

    public void toggleFuelPump() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_FUEL_PUMP_TOGGLE, 0);
    }


    public void toggleAutopilotAprHold() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_APR_HOLD, 0);
    }

    public void toggleAutopilotNav1Hold() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_NAV1_HOLD, 0);
    }

    public void toggleAutopilotVerticalSpeedHold() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_VS_HOLD, 0);
    }

    public void toggleAutopilotHeadingHold() {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_HDG_HOLD, 0);
    }

    private void sendEvent(int objectId, int eventId, int data) {
        try {
            sc.transmitClientEvent(objectId, eventId, data, NotificationPriority.HIGHEST.ordinal(), SimConnectConstants.EVENT_FLAG_GROUPID_IS_PRIORITY);
        } catch (IOException ioe) {
            // TODO
            ioe.printStackTrace(System.err);
        }
    }

    private void sendEventEx(int objectId, int eventId, int data0,int data1, int data2,int data3, int data4) {
        try {
            sc.transmitClientEventEx(objectId, eventId, NotificationPriority.HIGHEST.ordinal(), SimConnectConstants.EVENT_FLAG_GROUPID_IS_PRIORITY,data0,data1,data2,data3,data4);
        } catch (IOException ioe) {
            // TODO
            ioe.printStackTrace(System.err);
        }
    }

    public void start() {
        dt.createThread().start();
    }


    @Override
    public void handleException(SimConnect sender, RecvException e) {
        System.out.println("Exception : " + e.getException());
    }

    @Override
    public void handleOpen(SimConnect sender, RecvOpen e) {
        System.out.println("Connected to " + e.getApplicationName());
    }

    @Override
    public void handleEvent(SimConnect sender, RecvEvent e) {
        switch (e.getEventID()) {
            case CLIENT_EVENT_ID_SIM:
                simulationRunning = e.getData() == 1;
                break;
            case CLIENT_EVENT_ID_4_SEC:
                if (simulationRunning) {
//                    try {
//                        sender.requestFacilitiesList(FacilityListType.AIRPORT, CLIENT_EVENT_ID_AIRPORT_LIST);
//                    } catch (IOException e1) {
//                    }
                }
                break;
            default:
                System.out.println(e.getEventID() + " " + e.getGroupID());
        }
    }

    @Override
    public void handleAirportList(SimConnect sender, RecvAirportList list) {

    }

    @Override
    public void handleWaypointList(SimConnect sender, RecvWaypointList list) {

    }

    @Override
    public void handleVORList(SimConnect sender, RecvVORList list) {

    }

    @Override
    public void handleNDBList(SimConnect sender, RecvNDBList list) {

    }

    @Override
    public void handleSimObject(SimConnect sender, RecvSimObjectData e) {
        if (e.getRequestID() == DATA_REQUEST_ID_PLANE_POSITION && e.getDefineID() == DATA_DEFINITION_ID_PLANE_POSITION) {
            double userLat = e.getDataFloat64();
            double userLon = e.getDataFloat64();
            double userAlt = e.getDataFloat64();
            double altitudeAboveGroundInFeet = e.getDataFloat64();
            double altitudeAboveGroundMinusCenterOfGravityInFeet = e.getDataFloat64();
            double verticalSpeedFeetPerSecond = e.getDataFloat64();
            double airspeedIndicated = e.getDataFloat64();
            double airspeedTrue = e.getDataFloat64();
            double bankDegrees = e.getDataFloat64();
            double pitchDegrees = e.getDataFloat64();
            double headingDegreesGyro = e.getDataFloat64();
            double headingDegreesMagnetic = e.getDataFloat64();
            double headingDegreesTrue = e.getDataFloat64();
            firePlanePositionEvent(new PlanePositionEvent(userLat, userLon, userAlt, altitudeAboveGroundInFeet, altitudeAboveGroundMinusCenterOfGravityInFeet
                    , verticalSpeedFeetPerSecond, airspeedIndicated, airspeedTrue, bankDegrees, pitchDegrees, headingDegreesGyro, headingDegreesMagnetic, headingDegreesTrue));
        } else if (e.getRequestID() == DATA_REQUEST_ID_AUTOPILOT_STATE && e.getDefineID() == DATA_DEFINITION_ID_AUTOPILOT_STATE) {
            boolean autopilotMaster = e.getDataInt32() != 0;
            boolean headingLock = e.getDataInt32() != 0;
            double headingLockVar = e.getDataFloat64();
            boolean nav1Lock = e.getDataInt32() != 0;
            boolean verticalHold = e.getDataInt32() != 0;
            double verticalHoldVar = e.getDataFloat64();
            boolean altitudeHold = e.getDataInt32() != 0;
            double altitudeHoldVar = e.getDataFloat64();
            fireAutopilotEvent(
                    new AutopilotEvent(autopilotMaster, headingLock, headingLockVar, nav1Lock, verticalHold, verticalHoldVar, altitudeHold, altitudeHoldVar));
        } else if (e.getRequestID() == DATA_REQUEST_ID_SWITCHES && e.getDefineID() == DATA_DEFINITION_ID_SWITCHES) {
            boolean electricalMasterBattery = e.getDataInt32() != 0;
            boolean generalEngineMasterAlternator1 = e.getDataInt32() != 0;
            boolean generalEngineFuelPumpSwitch1 = e.getDataInt32() != 0;
            boolean lightLanding = e.getDataInt32() != 0;
            boolean lightNav = e.getDataInt32() != 0;
            boolean lightCabin = e.getDataInt32() != 0;
            boolean lightStrobe = e.getDataInt32() != 0;
            boolean lightBeacon = e.getDataInt32() != 0;
            boolean lightTaxi = e.getDataInt32() != 0;
            boolean lightRecognition = e.getDataInt32() != 0;
            boolean pitotHeatSwitch1 = e.getDataInt32() != 0;
            boolean avionicsMasterSwitch1 = e.getDataInt32() != 0;
            boolean brakeParkingIndicator = e.getDataInt32()!= 0;
            boolean brakeParkingPosition = e.getDataInt32()!= 0;
            fireSwitchesEvent(new SwitchesEvent(electricalMasterBattery, generalEngineMasterAlternator1, generalEngineFuelPumpSwitch1, lightLanding, lightNav, lightCabin, lightStrobe
                    , lightBeacon, lightTaxi, lightRecognition, pitotHeatSwitch1, avionicsMasterSwitch1,brakeParkingIndicator, brakeParkingPosition));
        }

    }

    private void fireSwitchesEvent(SwitchesEvent switchesEvent) {
        for (int idx = listeners.size() - 1; idx >= 0; idx--) {
            listeners.get(idx).handleSwitchesEvent(switchesEvent);
        }
    }

    private void fireAutopilotEvent(AutopilotEvent autopilotEvent) {
        for (int idx = listeners.size() - 1; idx >= 0; idx--) {
            listeners.get(idx).handleAutopilotEvent(autopilotEvent);
        }
    }

    private void firePlanePositionEvent(PlanePositionEvent planePositionEvent) {
        for (int idx = listeners.size() - 1; idx >= 0; idx--) {
            listeners.get(idx).handlePlanePositionEvent(planePositionEvent);
        }
    }

    void addListener(SimListener listener) {
        this.listeners.add(listener);
    }

    public void setHeadingBug(int degrees) {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_HEADING_BUG_SET, degrees);
    }
}
