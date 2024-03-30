package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SetInputEventRequest extends SimRequest {

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

    public SetInputEventRequest(long hash, float value) {
        super(TYPE_ID);
        this.hash = hash;
        this.unitSize = 4;
        data = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putFloat(value);
    }

    public SetInputEventRequest(long hash, String value) {
        super(TYPE_ID);
        this.hash = hash;
        byte[] valueBytes = value.getBytes(StandardCharsets.ISO_8859_1);
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

    public long getHash() {
        return hash;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public byte[] getData() {
        return data;
    }

    public float getFloatValue() {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getFloat();
    }

    public String getStringValue() {
        return new String(data, 0, data.length - 1, StandardCharsets.ISO_8859_1);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "hash=" + hash +
                ", unitSize=" + unitSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}