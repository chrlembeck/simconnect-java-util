package org.lembeck.fs.copilot.proxy.response;

import java.nio.ByteBuffer;

public abstract class SimResponse {

    private final int size;

    private final int version;

    private final int typeId;

    public SimResponse(int size, int version, int typeId) {
        this.size = size;
        this.version = version;
        this.typeId = typeId;
    }

    SimResponse(ByteBuffer buffer) {
        this(buffer.getInt(), // size
                buffer.getInt(), // version
                buffer.getInt()); // typeId
    }

    public int getSize() {
        return size;
    }

    public int getVersion() {
        return version;
    }

    public int getTypeId() {
        return typeId;
    }

    public static SimResponse parseResponse(int size, ByteBuffer buffer) {
        int typeId = buffer.getInt(8);
        return switch (typeId) {
            case 2 -> new HelloResponse(buffer);
            default -> new UnknownResponse(buffer);
        };
    }

    public static String toString(byte[] data) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i] & 0xff);
            if (i < data.length-1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}