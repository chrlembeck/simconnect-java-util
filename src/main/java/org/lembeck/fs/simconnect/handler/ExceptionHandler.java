package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.ExceptionResponse;

@FunctionalInterface
public interface ExceptionHandler {

    void handleException(ExceptionResponse response);
}