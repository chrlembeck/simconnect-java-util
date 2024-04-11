package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_PERIOD enumeration type is used with the SimConnect_RequestDataOnSimObject call to specify how often
 * data is to be sent to the client.
 */
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

    /**
     * Specified an unknown period (should not be used).
     */
    UNKNOWN;

    /**
     * Returns the simconnect period specified by the given identifier.
     *
     * @param id The identifier of the period.
     * @return Simconnect period specified by the given identifier.
     */
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