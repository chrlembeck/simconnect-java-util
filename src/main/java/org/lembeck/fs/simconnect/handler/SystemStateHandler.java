package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSystemStateResponse;

/**
 * Listener interface for RecvSystemStateResponse responses.
 */
@FunctionalInterface
public interface SystemStateHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param recvSystemStateResponse The response object the listener ist supposed to handle.
     */
    void handleSystemState(RecvSystemStateResponse recvSystemStateResponse);
}