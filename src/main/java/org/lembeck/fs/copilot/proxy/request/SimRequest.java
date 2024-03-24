package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.response.SimResponse;
import org.lembeck.fs.copilot.proxy.response.UnknownResponse;
import java.nio.ByteBuffer;

public abstract class SimRequest {

    public static final int MAGIC_FSX = (byte)'F' << 24 | (byte)'S' <<16 | (byte)'X' << 8 | 0;

    private final int size;

    private final int version;

    private final int typeId;

    private final int identifier;

    public SimRequest(int size, int version, int typeId, int identifier) {
        this.size = size;
        this.version = version;
        this.typeId = typeId;
        this.identifier = identifier;
    }

    SimRequest(ByteBuffer buffer) {
        this(buffer.getInt(), // size
                buffer.getInt(), // version
                buffer.getInt(), // typeId
                buffer.getInt()); // identifier
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

    public int getIdentifier() {
        return identifier;
    }

    public static SimRequest parseRequest(int size, ByteBuffer buffer) {
        int typeId = buffer.getInt(8);
        return switch (typeId) {
            case 0xf0000001 -> new HelloRequest(buffer);
            case 0xf000000c -> new AddToDataDefinitionRequest(buffer);
            case 0xf000000e -> new RequestDataOnSimObjectRequest(buffer);
            case 0xf0000017 -> new SubscribeToSystemEventRequest(buffer);
            default -> new UnknownRequest(buffer);
        };
    }
}