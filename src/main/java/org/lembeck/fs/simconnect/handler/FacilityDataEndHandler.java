package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvFacilityDataEndResponse;

/**
 * Listener interface for RecvFacilityDataEndResponse responses.
 */
@FunctionalInterface
public interface FacilityDataEndHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param facilityDataEndResponse The response object the listener ist supposed to handle.
     */
    void handleFacilityDataEnd(RecvFacilityDataEndResponse facilityDataEndResponse);
}