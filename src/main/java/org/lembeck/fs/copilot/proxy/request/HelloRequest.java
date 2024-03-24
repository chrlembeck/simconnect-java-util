package org.lembeck.fs.copilot.proxy.request;

import org.lembeck.fs.copilot.proxy.SimUtil;
import java.nio.ByteBuffer;

public class HelloRequest extends SimRequest {

    private final String name;
    private final int unknown;
    private final int magic;
    private final int majorVersion;
    private final int minorVersion;
    private final int majorBuildVersion;
    private final int minorBuildVersion;

    public HelloRequest(ByteBuffer buffer) {
        super(buffer);
        this.name = SimUtil.readString(buffer, 256);
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
                " {Typ: " + Integer.toHexString(getTypeId()) +
                ", Länge=" + getSize() +
                ", Version=" + getVersion() +
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