package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * The SimConnect_SetInputEvent function is used to set the value of a specific input event (identified by its
 * hash).
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm</a>
 */
public class SetInputEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000051;

    private final long hash;

    private final int unitSize;

    private final byte[] data;

    SetInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        hash = buffer.getLong();
        unitSize = buffer.getInt();
        data = new byte[unitSize];
        buffer.get(data);
    }

    /**
     * The SimConnect_SetInputEvent function is used to set the value of a specific input event (identified by its
     * hash).
     *
     * @param hash  Hash ID that will identify the desired inputEvent.
     * @param value New value of the specified inputEvent.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm</a>
     * @see #SetInputEventRequest(long, String)
     */
    public SetInputEventRequest(long hash, float value) {
        super(TYPE_ID);
        this.hash = hash;
        this.unitSize = 4;
        data = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putFloat(value);
    }

    /**
     * The SimConnect_SetInputEvent function is used to set the value of a specific input event (identified by its
     * hash).
     *
     * @param hash  Hash ID that will identify the desired inputEvent.
     * @param value New value of the specified inputEvent.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_SetInputEvent.htm</a>
     * @see #SetInputEventRequest(long, float)
     */
    public SetInputEventRequest(long hash, String value) {
        super(TYPE_ID);
        this.hash = hash;
        byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
        data = new byte[valueBytes.length + 1];
        this.unitSize = data.length;
        System.arraycopy(valueBytes, 0, data, 0, valueBytes.length);
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putLong(hash);
        outBuffer.putInt(unitSize);
        outBuffer.put(data);
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
     * Returns the size of the value in bytes.
     *
     * @return Size of the value in bytes.
     */
    public int getUnitSize() {
        return unitSize;
    }

    /**
     * Returns the value of the specified inputEvent.
     *
     * @return Value of the specified inputEvent.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Returns the value of the specified inputEvent as float value.
     *
     * @return Value of the specified inputEvent as float.
     */
    public float getFloatValue() {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getFloat();
    }

    /**
     * Returns the value of the specified inputEvent as string.
     *
     * @return Value of the specified inputEvent as string.
     */
    public String getStringValue() {
        return new String(data, 0, data.length - 1, StandardCharsets.UTF_8);
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
                ", unitSize=" + unitSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}