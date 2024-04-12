package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_UnsubscribeInputEvent function is used to unsubscribe from an input event that has previously been
 * subscribed to.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm</a>
 */
public class SubscribeInputEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000052;

    private final long hash;

    SubscribeInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        hash = buffer.getLong();
    }

    /**
     * The SimConnect_UnsubscribeInputEvent function is used to unsubscribe from an input event that has previously been
     * subscribed to.
     *
     * @param hash Hash ID that will identify the desired input event to unsubscribe from. You can use 0 here to
     *             unsubscribe from all input events.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm</a>
     */
    public SubscribeInputEventRequest(long hash) {
        super(TYPE_ID);
        this.hash = hash;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putLong(hash);
    }

    /**
     * Returns the hash ID that will identify the desired input event to unsubscribe from.
     *
     * @return Hash ID that will identify the desired input event to unsubscribe from.
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