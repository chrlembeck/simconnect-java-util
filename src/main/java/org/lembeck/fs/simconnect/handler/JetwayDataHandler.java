package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvJetwayDataResponse;

@FunctionalInterface

public interface JetwayDataHandler {

    void handleJetwayData(RecvJetwayDataResponse jetwayDataResponse);
}