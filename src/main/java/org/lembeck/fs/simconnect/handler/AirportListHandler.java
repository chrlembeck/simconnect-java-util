package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvAirportListResponse;

/**
 * Listener interface for RecvAirportListResponse responses.
 */
@FunctionalInterface
public interface AirportListHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param response The response object the listener ist supposed to handle.
     */
    void hanldeAirportList(RecvAirportListResponse response);
}