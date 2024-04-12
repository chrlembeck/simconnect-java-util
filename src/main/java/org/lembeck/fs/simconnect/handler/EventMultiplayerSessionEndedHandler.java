package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerSessionEndedResponse;

/**
 * Listener interface for RecvEventMultiplayerSessionEndedResponse responses.
 */
@FunctionalInterface
public interface EventMultiplayerSessionEndedHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param multiplayerSessionEndedResponse The response object the listener ist supposed to handle.
     */
    void handleEventMultiplayerSessionEnded(RecvEventMultiplayerSessionEndedResponse multiplayerSessionEndedResponse);
}