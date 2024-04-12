package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_OPEN structure is used to return information to the client, after a successful call to SimConnect_Open.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_OPEN.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_OPEN.htm</a>
 */
public class RecvOpenResponse extends SimResponse {

    private final String applicationName;

    private final int applicationVersionMajor;

    private final int applicationVersionMinor;

    private final int applicationBuildMajor;

    private final int applicationBuildMinor;

    private final int simConnectVersionMajor;

    private final int simConnectVersionMinor;

    private final int simConnectBuildMajor;

    private final int simConnectBuildMinor;

    private final int reserved1;

    private final int reserved2;

    RecvOpenResponse(ByteBuffer buffer) {
        super(buffer);
        applicationName = SimUtil.readString(buffer, 256);
        applicationVersionMajor = buffer.getInt();
        applicationVersionMinor = buffer.getInt();
        applicationBuildMajor = buffer.getInt();
        applicationBuildMinor = buffer.getInt();
        simConnectVersionMajor = buffer.getInt();
        simConnectVersionMinor = buffer.getInt();
        simConnectBuildMajor = buffer.getInt();
        simConnectBuildMinor = buffer.getInt();
        reserved1 = buffer.getInt();
        reserved2 = buffer.getInt();
    }

    /**
     * Returns a string containing the application name.
     *
     * @return String containing the application name.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Returns the application version major number.
     *
     * @return The application version major number.
     */
    public int getApplicationVersionMajor() {
        return applicationVersionMajor;
    }

    /**
     * Returns the application version minor number.
     *
     * @return The application version minor number.
     */
    public int getApplicationVersionMinor() {
        return applicationVersionMinor;
    }

    /**
     * Returns the application build major number.
     *
     * @return The application build major number.
     */
    public int getApplicationBuildMajor() {
        return applicationBuildMajor;
    }

    /**
     * Returns the application build minor number.
     *
     * @return The application build minor number.
     */
    public int getApplicationBuildMinor() {
        return applicationBuildMinor;
    }

    /**
     * Returns the SimConnect version major number.
     *
     * @return The SimConnect version major number.
     */
    public int getSimConnectVersionMajor() {
        return simConnectVersionMajor;
    }

    /**
     * Returns the SimConnect version minor number.
     *
     * @return The SimConnect version minor number.
     */
    public int getSimConnectVersionMinor() {
        return simConnectVersionMinor;
    }

    /**
     * Returns the SimConnect build major number.
     *
     * @return The SimConnect build major number.
     */
    public int getSimConnectBuildMajor() {
        return simConnectBuildMajor;
    }

    /**
     * Returns the SimConnect build minor number.
     *
     * @return The SimConnect build minor number.
     */
    public int getSimConnectBuildMinor() {
        return simConnectBuildMinor;
    }

    /**
     * Reserved.
     *
     * @return Reserved.
     */
    @Deprecated
    public int getReserved1() {
        return reserved1;
    }

    /**
     * Reserved.
     *
     * @return Reserved.
     */
    @Deprecated
    public int getReserved2() {
        return reserved2;
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
                ", applicationVersionMajor=" + applicationVersionMajor +
                ", applicationVersionMinor=" + applicationVersionMinor +
                ", applicationBuildMajor=" + applicationBuildMajor +
                ", applicationBuildMinor=" + applicationBuildMinor +
                ", simConnectVersionMajor=" + simConnectVersionMajor +
                ", simConnectVersionMinor=" + simConnectVersionMinor +
                ", simConnectBuildMajor=" + simConnectBuildMajor +
                ", simConnectBuildMinor=" + simConnectBuildMinor +
                ", reserved1=" + reserved1 +
                ", reserved2=" + reserved2 +
                "}";
    }
}