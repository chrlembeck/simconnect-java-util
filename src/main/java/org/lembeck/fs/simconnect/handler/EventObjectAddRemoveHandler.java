package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventObjectAddRemoveResponse;

/**
 * Listener interface for RecvEventObjectAddRemoveResponse responses.
 */
@FunctionalInterface
public interface EventObjectAddRemoveHandler {

    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param objectAddRemoveResponse The response object the listener ist supposed to handle.
     */
    void handleEventObjectAddRemove(RecvEventObjectAddRemoveResponse objectAddRemoveResponse);
}