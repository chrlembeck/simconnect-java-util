package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvQuitResponse;

/**
 * Listener interface for RecvQuitResponse responses.
 */
@FunctionalInterface
public interface QuitHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param quitResponse The response object the listener ist supposed to handle.
     */
    void handleQuit(RecvQuitResponse quitResponse);
}