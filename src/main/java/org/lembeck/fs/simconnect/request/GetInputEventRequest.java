package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_GetInputEvent function is used to retrieve the value of a specific input event (identified by its
 * hash).
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_GetInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_GetInputEvent.htm</a>
 */
public class GetInputEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000050;

    private final int requestID;

    private final long hash;

    GetInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        hash = buffer.getLong();
    }

    /**
     * The SimConnect_GetInputEvent function is used to retrieve the value of a specific input event (identified by its
     * hash).
     *
     * @param requestID The ID that will identify the current request in the response event.
     * @param hash      Hash ID that will identify the desired inputEvent.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_GetInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_GetInputEvent.htm</a>
     */
    public GetInputEventRequest(int requestID, long hash) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.hash = hash;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
        outBuffer.putLong(hash);
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
     * Returns the hash ID that will identify the desired inputEvent.
     *
     * @return Hash ID that will identify the desired inputEvent.
     */
    public long getHash() {
        return hash;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "GetInputEventRequest{" +
                "requestID=" + requestID +
                ", hash=" + hash +
                '}';
    }
}