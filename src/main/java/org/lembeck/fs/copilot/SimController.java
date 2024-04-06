package org.lembeck.fs.copilot;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.constants.SimconnectPeriod;
import org.lembeck.fs.simconnect.request.DataType;
import org.lembeck.fs.simconnect.request.EventFlag;
import org.lembeck.fs.simconnect.request.Priority;
import org.lembeck.fs.simconnect.response.RecvEventResponse;
import org.lembeck.fs.simconnect.response.RecvExceptionResponse;
import org.lembeck.fs.simconnect.response.RecvOpenResponse;
import org.lembeck.fs.simconnect.response.RecvSimobjectDataResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lembeck.fs.simconnect.SimUtil.OBJECT_ID_USER;

public class SimController {

    private SimConnect simConnect;

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

    private final int DATA_REQUEST_ID_PLANE_POSITION = SimConnect.getNextUserRequestID();
    private final int DATA_REQUEST_ID_AUTOPILOT_STATE = SimConnect.getNextUserRequestID();
    private final int DATA_REQUEST_ID_SWITCHES = SimConnect.getNextUserRequestID();

    private final int DATA_DEFINITION_ID_PLANE_POSITION = 1;
    private final int DATA_DEFINITION_ID_AUTOPILOT_STATE = 2;
    private final int DATA_DEFINITION_ID_SWITCHES = 3;

    private final List<SimListener> listeners = new ArrayList<>();

    public SimController(InetSocketAddress address) {
        try {
            simConnect = new SimConnect();

            simConnect.getRequestReceiver().addOpenHandler(this::handleOpen);
            simConnect.getRequestReceiver().addExceptionHandler(this::handleException);
            simConnect.getRequestReceiver().addEventHandler(this::handleEvent);
            simConnect.getRequestReceiver().addSimobjectDataHandler(this::handleSimObject);

            simConnect.connect(address, "SimController");

            simConnect.subscribeToSystemEvent(CLIENT_EVENT_ID_SIM, "Sim");
            simConnect.subscribeToSystemEvent(CLIENT_EVENT_ID_4_SEC, "4sec");


            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE LATITUDE", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE LONGITUDE", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALTITUDE", "FEET", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALT ABOVE GROUND", "FEET", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE ALT ABOVE GROUND MINUS CG", "FEET", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "VERTICAL SPEED", "FEET PER SECOND", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "AIRSPEED INDICATED", "KNOTS", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "AIRSPEED TRUE", "KNOTS", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE BANK DEGREES", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE PITCH DEGREES", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES GYRO", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES MAGNETIC", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_PLANE_POSITION, "PLANE HEADING DEGREES TRUE", "DEGREES", DataType.FLOAT64, 0);
            simConnect.requestDataOnSimObject(DATA_REQUEST_ID_PLANE_POSITION, DATA_DEFINITION_ID_PLANE_POSITION, OBJECT_ID_USER, SimconnectPeriod.SIM_FRAME, RecvSimobjectDataResponse.DATA_REQUEST_FLAG_CHANGED, 0, 0, 0);

            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT MASTER", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT HEADING LOCK", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT HEADING LOCK DIR", "DEGREES", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT NAV1 LOCK", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT VERTICAL HOLD", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT VERTICAL HOLD VAR", "feet/minute", DataType.FLOAT64, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT ALTITUDE LOCK", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_AUTOPILOT_STATE, "AUTOPILOT ALTITUDE LOCK VAR", "FEET", DataType.FLOAT64, 0);
            simConnect.requestDataOnSimObject(DATA_REQUEST_ID_AUTOPILOT_STATE, DATA_DEFINITION_ID_AUTOPILOT_STATE, OBJECT_ID_USER, SimconnectPeriod.SIM_FRAME, RecvSimobjectDataResponse.DATA_REQUEST_FLAG_CHANGED, 0, 0, 0);

            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "ELECTRICAL MASTER BATTERY", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "GENERAL ENG MASTER ALTERNATOR:1", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "GENERAL ENG FUEL PUMP SWITCH:1", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT LANDING", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT NAV", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT CABIN", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT STROBE", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT BEACON", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT TAXI", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "LIGHT RECOGNITION", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "PITOT HEAT SWITCH:1", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "AVIONICS MASTER SWITCH:1", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "BRAKE PARKING INDICATOR", "BOOL", DataType.INT32, 0);
            simConnect.addToDataDefinition(DATA_DEFINITION_ID_SWITCHES, "BRAKE PARKING POSITION", "BOOL", DataType.INT32, 0);
            simConnect.requestDataOnSimObject(DATA_REQUEST_ID_SWITCHES, DATA_DEFINITION_ID_SWITCHES, OBJECT_ID_USER, SimconnectPeriod.SIM_FRAME, RecvSimobjectDataResponse.DATA_REQUEST_FLAG_CHANGED, 0, 0, 0);

            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_MASTER, "AP_MASTER");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_ALT_HOLD, "AP_ALT_HOLD");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_APR_HOLD, "AP_APR_HOLD");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_HDG_HOLD, "AP_HDG_HOLD");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_NAV1_HOLD, "AP_NAV1_HOLD");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_VS_HOLD, "AP_VS_HOLD");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_HEADING_BUG_SET, "HEADING_BUG_SET");

            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_LANDING_LIGHTS_TOGGLE, "LANDING_LIGHTS_TOGGLE");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_RECOGNITION_LIGHTS, "TOGGLE_RECOGNITION_LIGHTS");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_NAV_LIGHTS, "TOGGLE_NAV_LIGHTS");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_TOGGLE, "STROBES_TOGGLE");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_ON, "STROBES_ON");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_STROBES_OFF, "STROBES_OFF");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_BEACON_LIGHTS, "TOGGLE_BEACON_LIGHTS");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_AVIONICS_MASTER, "TOGGLE_AVIONICS_MASTER");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_PITOT_HEAT_TOGGLE, "PITOT_HEAT_TOGGLE");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY, "TOGGLE_MASTER_BATTERY");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_TOGGLE_MASTER_BATTERY_ALTERNATOR_1, "TOGGLE_ALTERNATOR1");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_FUEL_PUMP_TOGGLE, "FUEL_PUMP");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_CABIN_LIGHTS_SET, "CABIN_LIGHTS_SET");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_PARKING_BRAKES, "PARKING_BRAKES");
            simConnect.mapClientEventToSimEvent(CLIENT_EVENT_ID_AP_VS_VAR_SET_ENGLISH, "AP_VS_VAR_SET_ENGLISH");

            addListener(new LoggingSimListener());
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
    }

    public void cabineLightsSet(int state, int index) {
        sendEvent(OBJECT_ID_USER, CLIENT_EVENT_ID_CABIN_LIGHTS_SET, (state & 0xFFFF) | ((index & 0xffff) << 16));
    }

    public void setVerticalSpeedAutopilotVar(int vsFeetPerMinute, int index) {
        sendEventEx(OBJECT_ID_USER, CLIENT_EVENT_ID_AP_VS_VAR_SET_ENGLISH, vsFeetPerMinute, index, 0, 0, 0);
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
            simConnect.transmitClientEvent(objectId, eventId, data, Priority.HIGHEST, EventFlag.EVENT_FLAG_GROUPID_IS_PRIORITY);
        } catch (IOException ioe) {
            // TODO
            ioe.printStackTrace(System.err);
        }
    }

    private void sendEventEx(int objectId, int eventId, int data0, int data1, int data2, int data3, int data4) {
        try {
            simConnect.transmitClientEventEx1(objectId, eventId, Priority.HIGHEST, EventFlag.EVENT_FLAG_GROUPID_IS_PRIORITY, data0, data1, data2, data3, data4);
        } catch (IOException ioe) {
            // TODO
            ioe.printStackTrace(System.err);
        }
    }

    public void handleException(RecvExceptionResponse exceptionResponse) {
        System.out.println("Exception : " + exceptionResponse);
    }

    public void handleOpen(RecvOpenResponse response) {
        System.out.println("Connected to " + response.getApplicationName());
    }

    public void handleEvent(RecvEventResponse response) {
        switch (response.getEventID()) {
            case CLIENT_EVENT_ID_SIM:
                simulationRunning = response.getData() == 1;
                break;
            case CLIENT_EVENT_ID_4_SEC:
                if (simulationRunning) {
                    // TODO
                }
                break;
            default:
                System.out.println(response.getEventID() + " " + response.getGroupID());
        }
    }

    public void handleSimObject(RecvSimobjectDataResponse e) {
        if (e.getRequestID() == DATA_REQUEST_ID_PLANE_POSITION && e.getDefineID() == DATA_DEFINITION_ID_PLANE_POSITION) {
            ByteBuffer data = e.getData();
            double userLat = data.getDouble();
            double userLon = data.getDouble();
            double userAlt = data.getDouble();
            double altitudeAboveGroundInFeet = data.getDouble();
            double altitudeAboveGroundMinusCenterOfGravityInFeet = data.getDouble();
            double verticalSpeedFeetPerSecond = data.getDouble();
            double airspeedIndicated = data.getDouble();
            double airspeedTrue = data.getDouble();
            double bankDegrees = data.getDouble();
            double pitchDegrees = data.getDouble();
            double headingDegreesGyro = data.getDouble();
            double headingDegreesMagnetic = data.getDouble();
            double headingDegreesTrue = data.getDouble();
            firePlanePositionEvent(new PlanePositionEvent(userLat, userLon, userAlt, altitudeAboveGroundInFeet, altitudeAboveGroundMinusCenterOfGravityInFeet
                    , verticalSpeedFeetPerSecond, airspeedIndicated, airspeedTrue, bankDegrees, pitchDegrees, headingDegreesGyro, headingDegreesMagnetic, headingDegreesTrue));
        } else if (e.getRequestID() == DATA_REQUEST_ID_AUTOPILOT_STATE && e.getDefineID() == DATA_DEFINITION_ID_AUTOPILOT_STATE) {
            ByteBuffer data = e.getData();
            boolean autopilotMaster = data.getInt() != 0;
            boolean headingLock = data.getInt() != 0;
            double headingLockVar = data.getDouble();
            boolean nav1Lock = data.getInt() != 0;
            boolean verticalHold = data.getInt() != 0;
            double verticalHoldVar = data.getDouble();
            boolean altitudeHold = data.getInt() != 0;
            double altitudeHoldVar = data.getDouble();
            fireAutopilotEvent(
                    new AutopilotEvent(autopilotMaster, headingLock, headingLockVar, nav1Lock, verticalHold, verticalHoldVar, altitudeHold, altitudeHoldVar));
        } else if (e.getRequestID() == DATA_REQUEST_ID_SWITCHES && e.getDefineID() == DATA_DEFINITION_ID_SWITCHES) {
            ByteBuffer data = e.getData();
            boolean electricalMasterBattery = data.getInt() != 0;
            boolean generalEngineMasterAlternator1 = data.getInt() != 0;
            boolean generalEngineFuelPumpSwitch1 = data.getInt() != 0;
            boolean lightLanding = data.getInt() != 0;
            boolean lightNav = data.getInt() != 0;
            boolean lightCabin = data.getInt() != 0;
            boolean lightStrobe = data.getInt() != 0;
            boolean lightBeacon = data.getInt() != 0;
            boolean lightTaxi = data.getInt() != 0;
            boolean lightRecognition = data.getInt() != 0;
            boolean pitotHeatSwitch1 = data.getInt() != 0;
            boolean avionicsMasterSwitch1 = data.getInt() != 0;
            boolean brakeParkingIndicator = data.getInt() != 0;
            boolean brakeParkingPosition = data.getInt() != 0;
            fireSwitchesEvent(new SwitchesEvent(electricalMasterBattery, generalEngineMasterAlternator1, generalEngineFuelPumpSwitch1, lightLanding, lightNav, lightCabin, lightStrobe
                    , lightBeacon, lightTaxi, lightRecognition, pitotHeatSwitch1, avionicsMasterSwitch1, brakeParkingIndicator, brakeParkingPosition));
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

    public SimConnect getSimConnect() {
        return simConnect;
    }
}
