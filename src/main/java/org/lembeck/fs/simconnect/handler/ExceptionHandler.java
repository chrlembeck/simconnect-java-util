package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvExceptionResponse;

@FunctionalInterface
public interface ExceptionHandler {

    void handleException(RecvExceptionResponse response);
}