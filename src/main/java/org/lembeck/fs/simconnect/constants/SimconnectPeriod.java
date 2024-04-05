package org.lembeck.fs.simconnect.constants;

public enum SimconnectPeriod {


    /**
     * Specifies that the data is not to be sent.
     */
    NEVER,

    /**
     * Specifies that the data should be sent once only. Note that this is not an efficient way of receiving data
     * frequently, use one of the other periods if there is a regular frequency to the data request.
     */
    ONCE,

    /**
     * Specifies that the data should be sent every visual (rendered) frame.
     */
    VISUAL_FRAME,

    /**
     * Specifies that the data should be sent every simulated frame, whether that frame is rendered or not.
     */
    SIM_FRAME,

    /**
     * Specifies that the data should be sent once every second.
     */
    SECOND,

    UNKNOWN;

    public static SimconnectPeriod ofId(int id) {
        return switch (id) {
            case 0 -> NEVER;
            case 1 -> ONCE;
            case 2 -> VISUAL_FRAME;
            case 3 -> SIM_FRAME;
            case 4 -> SECOND;
            default -> UNKNOWN;
        };
    }
}