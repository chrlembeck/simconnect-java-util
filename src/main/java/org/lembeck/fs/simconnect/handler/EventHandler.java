package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventResponse;

/**
 * Listener interface for RecvEventResponse responses.
 */
@FunctionalInterface
public interface EventHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void handleEvent(RecvEventResponse response);
}