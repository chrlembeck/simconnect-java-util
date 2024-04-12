package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT_FRAME structure is used with the SimConnect_SubscribeToSystemEvent call to return the frame
 * rate and simulation speed to the client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_FRAME.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_FRAME.htm</a>
 */
public class RecvEventFrameResponse extends RecvEventResponse {

    private final float frameRate;

    private final float simSpeed;

    RecvEventFrameResponse(ByteBuffer buffer) {
        super(buffer);
        frameRate = buffer.getFloat();
        simSpeed = buffer.getFloat();
    }

    /**
     * Returns the visual frame rate in frames per second.
     *
     * @return The visual frame rate in frames per second.
     */
    public float getFrameRate() {
        return frameRate;
    }

    /**
     * Returns the simulation rate. For example if the simulation is running at four times normal speed - 4X - then 4.0 will be returned.
     *
     * @return The simulation rate.
     */
    public float getSimSpeed() {
        return simSpeed;
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
                ", groupID=" + (groupID == UNKNOWN_GROUP ? "UNKNOWN_GROUP" : groupID) +
                ", eventID=" + eventID +
                ", data=" + data +
                ", frameRate=" + frameRate +
                ", simSpeed=" + simSpeed +
                "}";
    }
}