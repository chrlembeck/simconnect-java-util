package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerClientStartedResponse;

/**
 * Listener interface for RecvEventMultiplayerClientStartedResponse responses.
 */
@FunctionalInterface
public interface EventMultiplayerClientStartedHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param multiplayerClientStartedResponse The response object the listener ist supposed to handle.
     */
    void handleEventMultiplayerClientStarted(RecvEventMultiplayerClientStartedResponse multiplayerClientStartedResponse);
}