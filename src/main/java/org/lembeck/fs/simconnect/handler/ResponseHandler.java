package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.SimResponse;

@FunctionalInterface
public interface ResponseHandler {

    void handleResponse(SimResponse response);
}
