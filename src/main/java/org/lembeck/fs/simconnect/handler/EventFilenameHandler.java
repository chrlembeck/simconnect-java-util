package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventFilenameResponse;

/**
 * Listener interface for RecvEventFilenameResponse responses.
 */
@FunctionalInterface
public interface EventFilenameHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void handleEventFilename(RecvEventFilenameResponse response);
}