package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_EnumerateInputEventParams function is used to retrieve a list of all parameters from an input event.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEventParams.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEventParams.htm</a>
 */
public class EnumerateInputEventParamsRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000054;

    private final long hash;

    EnumerateInputEventParamsRequest(ByteBuffer buffer) {
        super(buffer);
        hash = buffer.getLong();
    }

    /**
     * The SimConnect_EnumerateInputEventParams function is used to retrieve a list of all parameters from an input
     * event.
     *
     * @param hash The ID that will identify the current request in the response event.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEventParams.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_EnumerateInputEventParams.htm</a>
     */
    public EnumerateInputEventParamsRequest(long hash) {
        super(TYPE_ID);
        this.hash = hash;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putLong(hash);
    }

    /**
     * Returns the ID that will identify the current request in the response event.
     *
     * @return ID that will identify the current request in the response event.
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
        return getClass().getSimpleName() + "{" +
                "hash=" + hash +
                '}';
    }
}