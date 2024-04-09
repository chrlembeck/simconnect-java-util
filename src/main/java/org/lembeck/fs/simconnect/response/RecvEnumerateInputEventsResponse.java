package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvEnumerateInputEventsResponse extends RecvListTemplate {

    private final InputEventDescriptor[] inputEventDescriptors;

    public RecvEnumerateInputEventsResponse(ByteBuffer buffer) {
        super(buffer);
        inputEventDescriptors = new InputEventDescriptor[getArraySize()];
        for (int i = 0; i < getArraySize(); i++) {
            inputEventDescriptors[i] = new InputEventDescriptor(buffer);
        }
    }

    public InputEventDescriptor[] getInputEventDescriptors() {
        return inputEventDescriptors;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "inputEventDescriptors=" + Arrays.toString(inputEventDescriptors) +
                '}';
    }
}