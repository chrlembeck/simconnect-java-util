package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

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

    public String getApplicationName() {
        return applicationName;
    }

    public int getApplicationVersionMajor() {
        return applicationVersionMajor;
    }

    public int getApplicationVersionMinor() {
        return applicationVersionMinor;
    }

    public int getApplicationBuildMajor() {
        return applicationBuildMajor;
    }

    public int getApplicationBuildMinor() {
        return applicationBuildMinor;
    }

    public int getSimConnectVersionMajor() {
        return simConnectVersionMajor;
    }

    public int getSimConnectVersionMinor() {
        return simConnectVersionMinor;
    }

    public int getSimConnectBuildMajor() {
        return simConnectBuildMajor;
    }

    public int getSimConnectBuildMinor() {
        return simConnectBuildMinor;
    }

    public int getReserved1() {
        return reserved1;
    }

    public int getReserved2() {
        return reserved2;
    }

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