package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvWaypointListResponse;

@FunctionalInterface
public interface WaypointListHandler {

    void hanldeWaypointList(RecvWaypointListResponse response);
}