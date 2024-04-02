package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerClientStartedResponse;

@FunctionalInterface
public interface EventMultiplayerClientStartedHandler {

    void handleEventMultiplayerClientStarted(RecvEventMultiplayerClientStartedResponse multiplayerClientStartedResponse);
}