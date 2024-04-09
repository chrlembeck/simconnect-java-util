package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventEx1Response;

@FunctionalInterface
public interface EventEx1Handler {

    void handleEventEx1(RecvEventEx1Response eventEx1Response);
}