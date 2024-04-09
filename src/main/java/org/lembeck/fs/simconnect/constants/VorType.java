package org.lembeck.fs.simconnect.constants;

public enum VorType {

    UNKNOWN,
    TERMINAL,
    LOW_ALTITUDE,
    LOW_ALT,
    HIGH_ALTITUDE,
    HIGH_ALT,
    ILS,
    VOT;

    public static VorType ofId(int id) {
        return switch (id) {
            case 0 -> UNKNOWN;
            case 1 -> TERMINAL;
            case 2 -> LOW_ALTITUDE;
            case 3 -> LOW_ALT;
            case 4 -> HIGH_ALTITUDE;
            case 5 -> HIGH_ALT;
            case 6 -> ILS;
            case 7 -> VOT;
            default -> UNKNOWN;
        };
    }
}