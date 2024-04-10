package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimConnect;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_CONTROLLERS_LIST structure is used to return a list of SIMCONNECT_CONTROLLER_ITEM structures.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_CONTROLLERS_LIST.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_CONTROLLERS_LIST.htm</a>
 * @see ControllerItem
 * @see SimConnect#enumerateControllers()
 */
public class RecvControllersListResponse extends RecvListTemplate {

    private final ControllerItem[] controllers;

    RecvControllersListResponse(ByteBuffer buffer) {
        super(buffer);
        controllers = new ControllerItem[getArraySize()];
        for (int i = 0; i < controllers.length; i++) {
            controllers[i] = new ControllerItem(buffer);
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "controllers=" + Arrays.toString(controllers) +
                '}';
    }

    /**
     * Returns the list of contained controller instances.
     *
     * @return The list of contained controller instances.
     */
    public ControllerItem[] getControllers() {
        return controllers;
    }
}