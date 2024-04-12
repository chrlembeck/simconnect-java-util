package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_VERSION_BASE_TYPE structure is used to hold version information about hardware components.
 */
public class VersionBaseType {

    /**
     * The major version number.
     */
    private final short major;

    /**
     * The minor version number.
     */
    private final short minor;

    /**
     * The revision number.
     */
    private final short revision;

    /**
     * The build ID.
     */
    private final short build;

    VersionBaseType(ByteBuffer buffer) {
        major = buffer.getShort();
        minor = buffer.getShort();
        revision = buffer.getShort();
        build = buffer.getShort();
    }

    /**
     * Creates a new version information object.
     *
     * @param major    The major version number.
     * @param minor    The minor version number.
     * @param revision The revision number.
     * @param build    The build ID.
     */
    public VersionBaseType(short major, short minor, short revision, short build) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
        this.build = build;
    }

    /**
     * Returns the major version number.
     *
     * @return The major version number.
     */
    public short getMajor() {
        return major;
    }

    /**
     * Returns the minor version number.
     *
     * @return The minor version number.
     */
    public short getMinor() {
        return minor;
    }

    /**
     * Returns the revision number.
     *
     * @return The revision number.
     */
    public short getRevision() {
        return revision;
    }

    /**
     * Returns the build ID.
     *
     * @return The build ID.
     */
    public short getBuild() {
        return build;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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