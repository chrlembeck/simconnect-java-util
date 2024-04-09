package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventFilenameResponse;

@FunctionalInterface
public interface EventFilenameHandler {

    void handleEventFilename(RecvEventFilenameResponse response);
}