package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvAssignedObjectIdResponse;

@FunctionalInterface
public interface AssignedObjectIdHandler {

    void handleAssignedObjectId(RecvAssignedObjectIdResponse assignedObjectIdResponse);
}