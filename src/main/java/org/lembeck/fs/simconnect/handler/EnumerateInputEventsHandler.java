package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEnumerateInputEventsResponse;

@FunctionalInterface
public interface EnumerateInputEventsHandler {

    void handleEnumerateInputEvents(RecvEnumerateInputEventsResponse enumerateInputEventsResponse);
}