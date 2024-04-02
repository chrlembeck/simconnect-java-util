package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvControllersListResponse;

@FunctionalInterface
public interface ControllersListHandler {

    void handleControllersList(RecvControllersListResponse controllersListResponse);
}
