package org.lembeck.fs.simconnect.constants;

import org.lembeck.fs.simconnect.request.Priority;

/**
 * Flag values used by the TransmitClientEvent request.
 *
 * @see org.lembeck.fs.simconnect.SimConnect#transmitClientEvent(int, int, int, Priority, int)
 * @see org.lembeck.fs.simconnect.SimConnect#transmitClientEventEx1(int, int, Priority, int, int, int, int, int, int)
 * @see org.lembeck.fs.simconnect.request.TransmitClientEventRequest#TransmitClientEventRequest(int, int, int, Priority, int)
 * @see org.lembeck.fs.simconnect.request.TransmitClientEventEx1Request#TransmitClientEventEx1Request(int, int, Priority, int, int, int, int, int, int)
 */
public interface EventFlag {

    /**
     * Default flag.
     */
    int EVENT_FLAG_DEFAULT = 0x00000000;

    /**
     * Set event repeat timer to simulate fast repeat.
     */
    int EVENT_FLAG_FAST_REPEAT_TIMER = 0x00000001;

    /**
     * Set event repeat timer to simulate slow repeat.
     */
    int EVENT_FLAG_SLOW_REPEAT_TIMER = 0x00000002;

    /**
     * Interpret GroupID parameter as priority value.
     */
    int EVENT_FLAG_GROUPID_IS_PRIORITY = 0x00000010;
}