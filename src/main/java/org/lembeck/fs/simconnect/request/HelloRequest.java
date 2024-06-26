package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The request that will be sent to establish the communication with the simulator.
 */
public class HelloRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000001;

    /**
     * Major version of the current simconnect client.
     */
    public final int MAJOR_VERSION = 11;
    /**
     * Minor version of the current simconnect client.
     */
    public final int MINOR_VERSION = 0;

    /**
     * Major build version of the current simconnect client.
     */
    public final int MAJOR_BUILD_VERSION = 62651;

    /**
     * Minor build version of the current simconnect client.
     */
    public final int MINOR_BUILD_VERSION = 3;

    /**
     * Magic key for FSX.
     */
    public static final int MAGIC_FSX = (byte) 'F' << 24 | (byte) 'S' << 16 | (byte) 'X' << 8 | 0;

    /**
     * Magic key for FS 2020.
     */
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

    /**
     * Creates a new request using the given name.
     *
     * @param name Name of the application.
     */
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

    /**
     * Returns the applications name.
     *
     * @return Applications name.
     */
    public String getName() {
        return name;
    }

    /**
     * The content of this int is not documented.
     *
     * @return I don't know.
     */
    public int getUnknown() {
        return unknown;
    }

    /**
     * Returns the magic bytes to specify the simconnect version.
     *
     * @return Magic bytes to specify the simconnect version.
     */
    public int getMagic() {
        return magic;
    }

    /**
     * Returns the SimConnect version major number.
     *
     * @return SimConnect version major number.
     */
    public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Returns the SimConnect version minor number.
     *
     * @return SimConnect version minor number.
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Returns the SimConnect build major number.
     *
     * @return SimConnect build major number.
     */
    public int getMajorBuildVersion() {
        return majorBuildVersion;
    }

    /**
     * Returns the SimConnect build minor number.
     *
     * @return SimConnect build minor number.
     */
    public int getMinorBuildVersion() {
        return minorBuildVersion;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", name='" + name + "'" +
                ", unknown=" + unknown +
                ", magic=" + magic +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", majorBuildVersion=" + majorBuildVersion +
                ", minorBuildVersion=" + minorBuildVersion +
                "}";
    }
}