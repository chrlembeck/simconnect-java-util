package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvClientDataResponse;

@FunctionalInterface
public interface ClientDataHandler {

    void handleClientData(RecvClientDataResponse clientDataResponse);
}