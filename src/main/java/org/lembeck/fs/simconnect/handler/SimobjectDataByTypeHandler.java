package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvSimobjectDataByTypeResponse;

@FunctionalInterface
public interface SimobjectDataByTypeHandler {

    void handleSimobjectDataByType(RecvSimobjectDataByTypeResponse simobjectDataByTypeResponse);
}
