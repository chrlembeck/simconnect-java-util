package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerServerStartedResponse;

/**
 * Listener interface for RecvEventMultiplayerServerStartedResponse responses.
 */
@FunctionalInterface
public interface EventMultiplayerServerStartedHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param multiplayerServerStartedResponse The response object the listener ist supposed to handle.
     */
    void handleEventMultiplayerServerStarted(RecvEventMultiplayerServerStartedResponse multiplayerServerStartedResponse);
}