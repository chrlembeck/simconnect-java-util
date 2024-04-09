package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventResponse;

@FunctionalInterface
public interface EventHandler {

    void handleEvent(RecvEventResponse response);
}