package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class InputEventDescriptor {

    private final String name;
    private final long hash;
    private final InputEventType inputEventType;

    public InputEventDescriptor(ByteBuffer buffer) {
        name = SimUtil.readString(buffer, 64);
        hash = buffer.getLong();
        inputEventType = InputEventType.values()[buffer.getInt()];
    }

    public String getName() {
        return name;
    }

    public long getHash() {
        return hash;
    }

    public InputEventType getInputEventType() {
        return inputEventType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", hash=" + hash +
                ", inputEventType=" + inputEventType +
                '}';
    }
}