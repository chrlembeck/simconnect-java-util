package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvOpenResponse;

/**
 * Listener interface for RecvOpenResponse responses.
 */
@FunctionalInterface
public interface OpenHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param openResponse The response object the listener ist supposed to handle.
     */
    void handleOpen(RecvOpenResponse openResponse);
}