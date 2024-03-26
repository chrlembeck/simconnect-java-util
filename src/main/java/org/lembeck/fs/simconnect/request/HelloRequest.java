package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class HelloRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000001;

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

    public HelloRequest(String name) {
        super(TYPE_ID);
        this.name = name;
        this.majorVersion = MAJOR_VERSION;
        this.minorVersion = MINOR_VERSION;
        this.majorBuildVersion = MAJOR_BUILD_VERSION;
        this.minorBuildVersion = MINOR_BUILD_VERSION;
        this.unknown = 0;
        this.magic = MAGIC_FS2020;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, name, 256);
        outBuffer.putInt(unknown);
        outBuffer.putInt(magic);
        outBuffer.putInt(majorVersion);
        outBuffer.putInt(minorVersion);
        outBuffer.putInt(majorBuildVersion);
        outBuffer.putInt(minorBuildVersion);
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