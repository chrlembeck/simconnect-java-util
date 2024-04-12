package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventEx1Response;

/**
 * Listener interface for RecvEventEx1Response responses.
 */
@FunctionalInterface
public interface EventEx1Handler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param eventEx1Response The response object the listener ist supposed to handle.
     */
    void handleEventEx1(RecvEventEx1Response eventEx1Response);
}