package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvReservedKeyResponse;

@FunctionalInterface
public interface ReservedKeyHandler {

    void handleReservedKey(RecvReservedKeyResponse reservedKeyResponse);
}