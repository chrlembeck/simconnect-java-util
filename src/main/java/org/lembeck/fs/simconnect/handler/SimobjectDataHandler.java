package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSimobjectDataResponse;

/**
 * Listener interface for RecvSimobjectDataResponse responses.
 */
@FunctionalInterface
public interface SimobjectDataHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param simobjectDataResponse The response object the listener ist supposed to handle.
     */
    void handleSimobjectData(RecvSimobjectDataResponse simobjectDataResponse);
}
