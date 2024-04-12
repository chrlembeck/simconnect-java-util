package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

/**
 * The SimConnect_FlightPlanLoad function is used to load an existing flight plan file.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm</a>
 */
public class FlightPlanLoadRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000003f;

    private final String filename;

    FlightPlanLoadRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
    }

    /**
     * The SimConnect_FlightPlanLoad function is used to load an existing flight plan file.
     *
     * @param filename String containing the path to the flight plan file. Flight plans have the extension .PLN, but no
     *                 need to enter an extension here. The easiest way to create flight plans is to create them from
     *                 within Microsoft Flight Simulator itself, and then save them off for use by the user or AI
     *                 controlled aircraft.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm</a>
     */
    public FlightPlanLoadRequest(String filename) {
        super(TYPE_ID);
        this.filename = filename;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, filename, SimUtil.MAX_PATH);
    }

    /**
     * Returns the string containing the path to the flight plan file. Flight plans have the extension .PLN, but no need
     * to enter an extension here. The easiest way to create flight plans is to create them from within Microsoft Flight
     * Simulator itself, and then save them off for use by the user or AI controlled aircraft.
     *
     * @return String containing the path to the flight plan file.
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