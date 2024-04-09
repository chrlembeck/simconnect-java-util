package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventMultiplayerSessionEndedResponse;

@FunctionalInterface
public interface EventMultiplayerSessionEndedHandler {

    void handleEventMultiplayerSessionEnded(RecvEventMultiplayerSessionEndedResponse multiplayerSessionEndedResponse);
}