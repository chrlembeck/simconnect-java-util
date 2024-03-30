package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvControllersListResponse extends RecvListTemplate {

    private final ControllerItem[] controllers;

    RecvControllersListResponse(ByteBuffer buffer) {
        super(buffer);
        controllers = new ControllerItem[getArraySize()];
        for (int i = 0; i < controllers.length; i++) {
            controllers[i] = new ControllerItem(buffer);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "controllers=" + Arrays.toString(controllers) +
                '}';
    }
}