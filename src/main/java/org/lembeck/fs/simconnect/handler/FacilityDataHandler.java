package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvFacilityDataResponse;

@FunctionalInterface
public interface FacilityDataHandler {

    void handleFacilityData(RecvFacilityDataResponse facilityDataResponse);
}