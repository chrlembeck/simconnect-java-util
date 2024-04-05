package org.lembeck.fs.simconnect.constants;

public enum FacilityDataType {
    AIRPORT,
    RUNWAY,
    START,
    FREQUENCY,
    HELIPAD,
    APPROACH,
    APPROACH_TRANSITION,
    APPROACH_LEG,
    FINAL_APPROACH_LEG,
    MISSED_APPROACH_LEG,
    DEPARTURE,
    ARRIVAL,
    RUNWAY_TRANSITION,
    ENROUTE_TRANSITION,
    TAXI_POINT,
    TAXI_PARKING,
    TAXI_PATH,
    TAXI_NAME,
    JETWAY,
    VOR,
    NDB,
    WAYPOINT,
    ROUTE,
    PAVEMENT,
    APPROACH_LIGHTS,
    VASI,
    UNKNOWN;

    public static FacilityDataType ofId(int id) {
        return switch (id) {
            case 0 -> AIRPORT;
            case 1 -> RUNWAY;
            case 2 -> START;
            case 3 -> FREQUENCY;
            case 4 -> HELIPAD;
            case 5 -> APPROACH;
            case 6 -> APPROACH_TRANSITION;
            case 7 -> APPROACH_LEG;
            case 8 -> FINAL_APPROACH_LEG;
            case 9 -> MISSED_APPROACH_LEG;
            case 10 -> DEPARTURE;
            case 11 -> ARRIVAL;
            case 12 -> RUNWAY_TRANSITION;
            case 13 -> ENROUTE_TRANSITION;
            case 14 -> TAXI_POINT;
            case 15 -> TAXI_PARKING;
            case 16 -> TAXI_PATH;
            case 17 -> TAXI_NAME;
            case 18 -> JETWAY;
            case 19 -> VOR;
            case 20 -> NDB;
            case 21 -> WAYPOINT;
            case 22 -> ROUTE;
            case 23 -> PAVEMENT;
            case 24 -> APPROACH_LIGHTS;
            case 25 -> VASI;
            default -> UNKNOWN;
        };
    }
}