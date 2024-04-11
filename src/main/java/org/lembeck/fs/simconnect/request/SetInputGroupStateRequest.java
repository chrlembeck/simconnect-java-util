package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.State;

import java.nio.ByteBuffer;

/**
 * The SimConnect_SetInputGroupState function is used to turn requests for input event information from the server on and off.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm</a>
 */
public class SetInputGroupStateRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000015;

    private final int groupID;

    private final State state;

    SetInputGroupStateRequest(ByteBuffer buffer) {
        super(buffer);
        this.groupID = buffer.getInt();
        this.state = State.ofId(buffer.getInt());
    }

    /**
     * The SimConnect_SetInputGroupState function is used to turn requests for input event information from the server on and off.
     *
     * @param groupID Specifies the ID of the client defined input group that is to have its state changed.
     * @param state   Double word containing the new state. One member of the SIMCONNECT_STATE enumeration type.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm</a>
     * @see State
     */
    public SetInputGroupStateRequest(int groupID, State state) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.state = state;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        outBuffer.putInt(state.ordinal());
    }

    /**
     * Returns the ID of the client defined input group that is to have its state changed.
     *
     * @return ID of the client defined input group that is to have its state changed.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns the new state. One member of the SIMCONNECT_STATE enumeration type.
     *
     * @return New state. One member of the SIMCONNECT_STATE enumeration type.
     */
    public State getState() {
        return state;
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
                ", groupID=" + groupID +
                ", state=" + state +
                "}";
    }
}