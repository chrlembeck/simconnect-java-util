package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvControllersListResponse;

/**
 * Listener interface for RecvControllersListResponse responses.
 */
@FunctionalInterface
public interface ControllersListHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param controllersListResponse The response object the listener ist supposed to handle.
     */
    void handleControllersList(RecvControllersListResponse controllersListResponse);
}
