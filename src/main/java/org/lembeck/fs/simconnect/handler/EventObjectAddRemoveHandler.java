package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventObjectAddRemoveResponse;

@FunctionalInterface
public interface EventObjectAddRemoveHandler {

    void handleEventObjectAddRemove(RecvEventObjectAddRemoveResponse objectAddRemoveResponse);
}