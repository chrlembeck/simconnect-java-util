package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.InputEventType;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_INPUT_EVENT_DESCRIPTOR structure is used to return an item of data for a specific input event.
 */
public class InputEventDescriptor {

    /**
     * The name of the Input Event.
     */
    private final String name;

    /**
     * The hash ID for the event.
     */
    private final long hash;

    /**
     * The expected datatype (from the SIMCONNECT_DATATYPE enum). Usually a FLOAT32 or STRING128.
     */
    private final InputEventType inputEventType;

    InputEventDescriptor(ByteBuffer buffer) {
        name = SimUtil.readString(buffer, 64);
        hash = buffer.getLong();
        inputEventType = InputEventType.ofId(buffer.getInt());
    }

    /**
     * Creates a new InputEventDescriptor instance.
     *
     * @param name           The name of the Input Event.
     * @param hash           The hash ID for the event.
     * @param inputEventType The expected datatype (from the SIMCONNECT_DATATYPE enum). Usually a FLOAT32 or STRING128.
     */
    public InputEventDescriptor(String name, long hash, InputEventType inputEventType) {
        this.name = name;
        this.hash = hash;
        this.inputEventType = inputEventType;
    }

    /**
     * Returns the name of the Input Event.
     *
     * @return The name of the Input Event.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hash ID for the event.
     *
     * @return The hash ID for the event.
     */
    public long getHash() {
        return hash;
    }

    /**
     * Returns the expected datatype (from the SIMCONNECT_DATATYPE enum). Usually a FLOAT32 or STRING128.
     *
     * @return The expected datatype (from the SIMCONNECT_DATATYPE enum). Usually a FLOAT32 or STRING128.
     */
    public InputEventType getInputEventType() {
        return inputEventType;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", hash=" + hash +
                ", inputEventType=" + inputEventType +
                '}';
    }
}