package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEnumerateInputEventsResponse;

/**
 * Listener interface for RecvEnumerateInputEventsResponse responses.
 */
@FunctionalInterface
public interface EnumerateInputEventsHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param enumerateInputEventsResponse The response object the listener ist supposed to handle.
     */
    void handleEnumerateInputEvents(RecvEnumerateInputEventsResponse enumerateInputEventsResponse);
}