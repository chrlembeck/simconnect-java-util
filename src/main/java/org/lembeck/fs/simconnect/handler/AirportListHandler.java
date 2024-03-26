package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvAirportListResponse;

@FunctionalInterface
public interface AirportListHandler {

    void hanldeAirportList(RecvAirportListResponse response);
}