package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvFacilityDataResponse;

/**
 * Listener interface for RecvFacilityDataResponse responses.
 */
@FunctionalInterface
public interface FacilityDataHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param facilityDataResponse The response object the listener ist supposed to handle.
     */
    void handleFacilityData(RecvFacilityDataResponse facilityDataResponse);
}