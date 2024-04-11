package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.SimResponse;

/**
 * Listener interface for SimResponse responses.
 */
@FunctionalInterface
public interface ResponseHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void handleResponse(SimResponse response);
}
