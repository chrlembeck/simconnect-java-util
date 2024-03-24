package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.SimUtil;

import java.nio.ByteBuffer;

public class HelloRequest extends SimRequest {

    public final int MAJOR_VERSION = 11;
    public final int MINOR_VERSION = 0;
    public final int MAJOR_BUILD_VERSION = 62651;
    public final int MINOR_BUILD_VERSION = 3;

    public static final int MAGIC_FSX = (byte) 'F' << 24 | (byte) 'S' << 16 | (byte) 'X' << 8 | 0;
    public final int MAGIC_FS2020 = 0x4b4b00;

    private final String name;
    private final int unknown;
    private final int magic;
    private final int majorVersion;
    private final int minorVersion;
    private final int majorBuildVersion;
    private final int minorBuildVersion;

    HelloRequest(ByteBuffer buffer) {
        super(buffer);
        name = SimUtil.readString(buffer, 256);
        unknown = buffer.getInt(); // always 0
        magic = buffer.getInt();
        majorVersion = buffer.getInt();
        minorVersion = buffer.getInt();
        majorBuildVersion = buffer.getInt();
        minorBuildVersion = buffer.getInt();
    }

    public String getName() {
        return name;
    }

    public int getUnknown() {
        return unknown;
    }

    public int getMagic() {
        return magic;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorBuildVersion() {
        return majorBuildVersion;
    }

    public int getMinorBuildVersion() {
        return minorBuildVersion;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", name='" + name + "'"+
                ", unknown=" + unknown +
                ", magic=" + magic +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", majorBuildVersion=" + majorBuildVersion +
                ", minorBuildVersion=" + minorBuildVersion +
                "}";
    }
}