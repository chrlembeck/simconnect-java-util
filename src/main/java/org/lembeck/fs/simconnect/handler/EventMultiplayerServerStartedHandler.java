package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerServerStartedResponse;

@FunctionalInterface
public interface EventMultiplayerServerStartedHandler {

    void handleEventMultiplayerServerStarted(RecvEventMultiplayerServerStartedResponse multiplayerServerStartedResponse);
}