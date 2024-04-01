package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSimobjectDataResponse;

@FunctionalInterface
public interface SimobjectDataHandler {

    void handleSimobjectData(RecvSimobjectDataResponse simobjectDataResponse);
}
