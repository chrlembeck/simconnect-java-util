package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

/**
 * The SimConnect_FlightSave function is used to save the current state of a flight to a flight file.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm</a>
 */
public class FlightSaveRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000003e;

    private final String filename;

    private final String description;

    private final int flags;

    private final String title;

    FlightSaveRequest(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        title = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        description = SimUtil.readString(buffer, 2048);
        flags = buffer.getInt();
    }

    /**
     * The SimConnect_FlightSave function is used to save the current state of a flight to a flight file.
     *
     * @param filename    String containing the path to the flight file. Flight files have the extension .FLT, but no
     *                    need to enter an extension here.
     * @param title       String containing the title of the flight file. If this is NULL then the szFileName parameter
     *                    is used as the title.
     * @param description String containing the text to the description field of the flight file.
     * @param flags       Unused.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm</a>
     */
    public FlightSaveRequest(String filename, String title, String description, int flags) {
        super(TYPE_ID);
        this.filename = filename;
        this.title = title;
        this.description = description;
        this.flags = flags;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, filename, SimUtil.MAX_PATH);
        SimUtil.writeString(outBuffer, title, SimUtil.MAX_PATH);
        SimUtil.writeString(outBuffer, description, 2048);
        outBuffer.putInt(flags);
    }

    /**
     * Returns the string containing the path to the flight file. Flight files have the extension .FLT, but no
     * need to enter an extension here.
     *
     * @return String containing the path to the flight file.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns the string containing the title of the flight file. If this is NULL then the szFileName parameter
     * is used as the title.
     *
     * @return String containing the title of the flight file.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the string containing the text to the description field of the flight file.
     *
     * @return String containing the text to the description field of the flight file.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Unused.
     *
     * @return Unused.
     */
    @Deprecated
    public int getFlags() {
        return flags;
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
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", flags=" + flags +
                '}';
    }
}