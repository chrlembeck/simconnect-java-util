package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_EnumerateInputEvents function is used to retrieve a paginated list of all available InputEvents
 * for the current aircraft along with their associated hash (CRC based).
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEvents.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEvents.htm</a>
 */
public class EnumerateInputEventsRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004f;

    private final int requestID;

    EnumerateInputEventsRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_EnumerateInputEvents function is used to retrieve a paginated list of all available InputEvents
     * for the current aircraft along with their associated hash (CRC based).
     *
     * @param requestID The ID that will identify the current request in the response event.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEvents.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEvents.htm</a>
     */
    public EnumerateInputEventsRequest(int requestID) {
        super(TYPE_ID);
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
    }

    /**
     * Returns the ID that will identify the current request in the response event.
     *
     * @return ID that will identify the current request in the response event.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}