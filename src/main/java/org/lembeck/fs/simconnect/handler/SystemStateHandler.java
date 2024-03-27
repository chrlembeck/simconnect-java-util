package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSystemStateResponse;

@FunctionalInterface
public interface SystemStateHandler {

    void handleSystemState(RecvSystemStateResponse recvSystemStateResponse);
}