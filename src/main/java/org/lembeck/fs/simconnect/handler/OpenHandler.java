package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvOpenResponse;

@FunctionalInterface
public interface OpenHandler {

    void handleOpen(RecvOpenResponse openResponse);
}