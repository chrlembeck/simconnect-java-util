package org.lembeck.fs.simconnect.constants;

public enum RunwayDesignator {

    NONE, LEFT, RIGHT, CENTER, WATER, A, B, LAST, UNKNOWN;

    public static RunwayDesignator ofId(int id) {
        return switch (id) {
            case 0 -> NONE;
            case 1 -> LEFT;
            case 2 -> RIGHT;
            case 3 -> CENTER;
            case 4 -> WATER;
            case 5 -> A;
            case 6 -> B;
            case 7 -> LAST;
            default -> UNKNOWN;
        };
    }

    public String getShortName() {
        return switch (this) {
            case A -> "A";
            case B -> "B";
            case LEFT -> "L";
            case RIGHT -> "R";
            case WATER -> "W";
            case CENTER -> "C";
            case LAST -> "LAST";
            case UNKNOWN -> "UNKNOWN";
            case NONE -> "";
        };
    }
}
