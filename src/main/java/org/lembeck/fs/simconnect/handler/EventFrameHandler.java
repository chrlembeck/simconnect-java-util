package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvEventFrameResponse;

/**
 * Listener interface for RecvEventFrameResponse responses.
 */
@FunctionalInterface
public interface EventFrameHandler {
    /**
     * Defines the method, wich the response receiver should give the specific response to when the listener will be
     * registered to it.
     *
     * @param eventFrameResponse The response object the listener ist supposed to handle.
     */
    void handleEventFrame(RecvEventFrameResponse eventFrameResponse);
}