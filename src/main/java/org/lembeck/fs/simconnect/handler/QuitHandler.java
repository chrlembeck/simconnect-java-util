package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvQuitResponse;

@FunctionalInterface
public interface QuitHandler {

    void handleQuit(RecvQuitResponse quitRsponse);
}