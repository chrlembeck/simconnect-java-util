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

    public int getTypeID() {
        return typeId;
    }

    public static SimResponse parseResponse(int size, ByteBuffer buffer) {
        int typeId = buffer.getInt(8);
        return switch (typeId) {
            case 0x01 -> new ExceptionResponse(buffer);
            case 0x02 -> new HelloResponse(buffer);
            case 0x03 -> new QuitResponse(buffer);
            case 0x04 -> new RecvEventResponse(buffer);
            case 0x08 -> new RecvSimobjectDataResponse(buffer);
            case 0x09 -> new RecvSimobjectDataByTypeResponse(buffer);
            case 0x16 -> new RecvEventMultiplayerServerStartedResponse(buffer);
            case 0x17 -> new RecvEventMultiplayerClientStartedResponse(buffer);
            case 0x18 -> new RecvEventMultiplayerSessionEndedResponse(buffer);
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