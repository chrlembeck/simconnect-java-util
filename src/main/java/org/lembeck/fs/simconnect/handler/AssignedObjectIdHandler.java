package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvAssignedObjectIdResponse;

/**
 * Listener interface for RecvAssignedObjectIdResponse responses.
 */
@FunctionalInterface
public interface AssignedObjectIdHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param assignedObjectIdResponse The response object the listener ist supposed to handle.
     */
    void handleAssignedObjectId(RecvAssignedObjectIdResponse assignedObjectIdResponse);
}