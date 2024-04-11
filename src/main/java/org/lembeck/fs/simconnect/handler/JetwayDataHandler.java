package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvJetwayDataResponse;

/**
 * Listener interface for RecvJetwayDataResponse responses.
 */
@FunctionalInterface
public interface JetwayDataHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param jetwayDataResponse The response object the listener ist supposed to handle.
     */
    void handleJetwayData(RecvJetwayDataResponse jetwayDataResponse);
}