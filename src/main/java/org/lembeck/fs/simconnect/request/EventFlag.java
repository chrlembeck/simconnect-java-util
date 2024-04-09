package org.lembeck.fs.simconnect.request;

public interface EventFlag {

    int EVENT_FLAG_DEFAULT = 0x00000000;
    int EVENT_FLAG_FAST_REPEAT_TIMER = 0x00000001;      // set event repeat timer to simulate fast repeat
    int EVENT_FLAG_SLOW_REPEAT_TIMER = 0x00000002;      // set event repeat timer to simulate slow repeat
    int EVENT_FLAG_GROUPID_IS_PRIORITY = 0x00000010;      // interpret GroupID parameter as priority value
}