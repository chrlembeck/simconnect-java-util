package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventFrameResponse;

@FunctionalInterface
public interface EventFrameHandler {
    void handleEventFrame(RecvEventFrameResponse eventFrameResponse);
}