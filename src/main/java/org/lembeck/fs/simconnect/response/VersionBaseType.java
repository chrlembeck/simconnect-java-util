package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class VersionBaseType {

    private final short major;

    private final short minor;

    private final short revision;

    private final short build;

    public VersionBaseType(ByteBuffer buffer) {
        major = buffer.getShort();
        minor = buffer.getShort();
        revision = buffer.getShort();
        build = buffer.getShort();
    }

    public short getMajor() {
        return major;
    }

    public short getMinor() {
        return minor;
    }

    public short getRevision() {
        return revision;
    }

    public short getBuild() {
        return build;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "major=" + major +
                ", minor=" + minor +
                ", revision=" + revision +
                ", build=" + build +
                '}';
    }
}