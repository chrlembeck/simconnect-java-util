package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSimobjectDataByTypeResponse;

/**
 * Listener interface for RecvSimobjectDataByTypeResponse responses.
 */
@FunctionalInterface
public interface SimobjectDataByTypeHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param simobjectDataByTypeResponse The response object the listener ist supposed to handle.
     */
    void handleSimobjectDataByType(RecvSimobjectDataByTypeResponse simobjectDataByTypeResponse);
}
