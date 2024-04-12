package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_EnumerateControllers function is used to retrieve a list of every device that is currently plugged
 * into the simulation.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateControllers.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateControllers.htm</a>
 */
public class EnumerateControllersRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004c;

    EnumerateControllersRequest(ByteBuffer buffer) {
        super(buffer);
    }

    /**
     * The SimConnect_EnumerateControllers function is used to retrieve a list of every device that is currently plugged
     * into the simulation.
     *
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateControllers.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateControllers.htm</a>
     */
    public EnumerateControllersRequest() {
        super(TYPE_ID);
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        // nothing to write here
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}