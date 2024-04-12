package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_ENUMERATE_INPUT_EVENTS structure is used to return a single page of data about an input event.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_ENUMERATE_INPUT_EVENTS.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_ENUMERATE_INPUT_EVENTS.htm</a>
 */
public class RecvEnumerateInputEventsResponse extends RecvListTemplate {

    private final InputEventDescriptor[] inputEventDescriptors;

    RecvEnumerateInputEventsResponse(ByteBuffer buffer) {
        super(buffer);
        inputEventDescriptors = new InputEventDescriptor[getArraySize()];
        for (int i = 0; i < getArraySize(); i++) {
            inputEventDescriptors[i] = new InputEventDescriptor(buffer);
        }
    }

    /**
     * Returns the array of SIMCONNECT_INPUT_EVENT_DESCRIPTOR structures.
     *
     * @return Array of SIMCONNECT_INPUT_EVENT_DESCRIPTOR structures.
     */
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