package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

/**
 * The SimConnect_FlightLoad function is used to load an existing flight file.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm</a>
 */
public class FlightLoadRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000003d;

    private final String filename;

    FlightLoadRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    /**
     * The SimConnect_FlightLoad function is used to load an existing flight file.
     *
     * @param filename String containing the path to the flight file.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm</a>
     */
    public FlightLoadRequest(String filename) {
        super(TYPE_ID);
        this.filename = filename;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, filename, SimUtil.MAX_PATH);
    }

    /**
     * Returns the string containing the path to the flight file.
     *
     * @return String containing the path to the flight file.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "filename='" + filename + '\'' +
                '}';
    }
}