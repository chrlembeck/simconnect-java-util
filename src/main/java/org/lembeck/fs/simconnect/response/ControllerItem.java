package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class ControllerItem {

    private final String deviceName;
    private final int deviceID;
    private final int productID;
    private final int compositeID;
    private final VersionBaseType hardwareVersion;

    ControllerItem(ByteBuffer buffer) {
        deviceName = SimUtil.readString(buffer, 256);
        deviceID = buffer.getInt();
        productID = buffer.getInt();
        compositeID = buffer.getInt();
        hardwareVersion = new VersionBaseType(buffer);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public int getProductID() {
        return productID;
    }

    public int getCompositeID() {
        return compositeID;
    }

    public VersionBaseType getHardwareVersion() {
        return hardwareVersion;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceID=" + deviceID +
                ", productID=" + productID +
                ", compositeID=" + compositeID +
                ", hardwareVersion=" + hardwareVersion +
                '}';
    }
}