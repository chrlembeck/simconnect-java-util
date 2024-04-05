package org.lembeck.fs.simconnect.request;

public enum FacilityListType {
    AIRPORT,
    WAYPOINT,
    NDB,
    VOR,
    COUNT,
    UNKNOWN;

    public static FacilityListType ofId(int id) {
        return switch (id) {
            case 0 -> AIRPORT;
            case 1 -> WAYPOINT;
            case 2 -> NDB;
            case 3 -> VOR;
            case 4 -> COUNT;
            default -> UNKNOWN;
        };
    }
}