package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvReservedKeyResponse;

/**
 * Listener interface for RecvReservedKeyResponse responses.
 */
@FunctionalInterface
public interface ReservedKeyHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param reservedKeyResponse The response object the listener ist supposed to handle.
     */
    void handleReservedKey(RecvReservedKeyResponse reservedKeyResponse);
}