package org.lembeck.fs.simconnect.constants;

public enum InputEventType {
    DOUBLE,
    STRING,
    UNKNOWN;

    public static InputEventType ofId(int id) {
        return switch (id) {
            case 0 -> DOUBLE;
            case 1 -> STRING;
            default -> UNKNOWN;
        };
    }
}