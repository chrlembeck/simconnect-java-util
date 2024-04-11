package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvWaypointListResponse;

/**
 * Listener interface for RecvWaypointListResponse responses.
 */
@FunctionalInterface
public interface WaypointListHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void hanldeWaypointList(RecvWaypointListResponse response);
}