package org.lembeck.fs.simconnect.constants;

public enum State {

    OFF,

    ON,

    UNKNOWN;

    public static State ofId(int id) {
        return switch (id) {
            case 0 -> OFF;
            case 1 -> ON;
            default -> UNKNOWN;
        };
    }
}