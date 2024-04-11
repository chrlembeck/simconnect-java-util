package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvExceptionResponse;

/**
 * Listener interface for RecvExceptionResponse responses.
 */
@FunctionalInterface
public interface ExceptionHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void handleException(RecvExceptionResponse response);
}