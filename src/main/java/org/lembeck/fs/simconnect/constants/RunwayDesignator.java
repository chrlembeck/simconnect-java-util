package org.lembeck.fs.simconnect.constants;

/**
 * Enumeration of the possible runway designators.
 */
public enum RunwayDesignator {

    /**
     * Runway has no specific designator.
     */
    NONE,

    /**
     * Left runway.
     */
    LEFT,

    /**
     * Right runway.
     */
    RIGHT,

    /**
     * Center runway.
     */
    CENTER,

    /**
     * Water runway.
     */
    WATER,

    /**
     * Runway A.
     */
    A,

    /**
     * Runway B.
     */
    B,

    /**
     * Last runway.
     */
    LAST,

    /**
     * Unknown runway designator.
     */
    UNKNOWN;

    /**
     * Returns the runway designator specified by the given identifier.
     *
     * @param id Identifier of the runway designator.
     * @return The runway designator specified by the given identifier.
     */
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

    /**
     * Returns a short name of the runway designator.
     *
     * @return Short name of the designator (like 'L' for left...).
     */
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