package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvClientDataResponse;

/**
 * Listener interface for RecvClientDataResponse responses.
 */
@FunctionalInterface
public interface ClientDataHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param clientDataResponse The response object the listener ist supposed to handle.
     */
    void handleClientData(RecvClientDataResponse clientDataResponse);
}