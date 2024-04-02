package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvFacilityDataEndResponse;

@FunctionalInterface
public interface FacilityDataEndHandler {

    void handleFacilityDataEnd(RecvFacilityDataEndResponse facilityDataEndResponse);
}