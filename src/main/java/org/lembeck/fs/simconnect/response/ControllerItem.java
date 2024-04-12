package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimConnect;
import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_CONTROLLER_ITEM struct contains data related to a single controller currently connected to the simulation.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_CONTROLLER_ITEM.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_CONTROLLER_ITEM.htm</a>
 * @see SimConnect#enumerateControllers()
 */
public class ControllerItem {

    /**
     * A string that gives the descriptive name for the device.
     */
    private final String deviceName;

    /**
     * The device ID.
     */
    private final int deviceID;

    /**
     * The product ID.
     */
    private final int productID;

    /**
     * ID of the USB composite device (for when devices have the same ProductId, but there are multiple recognised parts on the same device).
     */
    private final int compositeID;

    /**
     * The version data for the hardware, returned as a SIMCONNECT_VERSION_BASE_TYPE struct. Note that members of this struct will be 0, as it is not currently used in the simulation.
     */
    private final VersionBaseType hardwareVersion;

    ControllerItem(ByteBuffer buffer) {
        deviceName = SimUtil.readString(buffer, 256);
        deviceID = buffer.getInt();
        productID = buffer.getInt();
        compositeID = buffer.getInt();
        hardwareVersion = new VersionBaseType(buffer);
    }

    /**
     * Returns the descriptive name for the device.
     *
     * @return The descriptive name for the device
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Returns the device ID.
     *
     * @return The device ID.
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * Returns the product ID.
     *
     * @return The product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Returns the ID of the USB composite device (for when devices have the same ProductId, but there are multiple recognised parts on the same device).
     *
     * @return The ID of the USB composite device.
     */
    public int getCompositeID() {
        return compositeID;
    }

    /**
     * Returns the version data for the hardware, returned as a SIMCONNECT_VERSION_BASE_TYPE struct. Note that members of this struct will be 0, as it is not currently used in the simulation.
     *
     * @return Version data for the hardware.
     */
    public VersionBaseType getHardwareVersion() {
        return hardwareVersion;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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