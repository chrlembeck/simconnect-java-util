package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.State;
import java.nio.ByteBuffer;

/**
 * The SimConnect_SetSystemEventState function is used to turn requests for event information from the server on and
 * off.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm</a>
 */
public class SetSystemEventStateRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000006;
    private final int clientEventID;
    private final State state;

    SetSystemEventStateRequest(ByteBuffer buffer) {
        super(buffer);
        clientEventID = buffer.getInt();
        state = State.ofId(buffer.getInt());
    }

    /**
     * The SimConnect_SetSystemEventState function is used to turn requests for event information from the server on and
     * off.
     *
     * @param clientEventID Specifies the ID of the client event that is to have its state changed.
     * @param state         Double word containing the state (one member of SIMCONNECT_STATE).
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm</a>
     */
    public SetSystemEventStateRequest(int clientEventID, State state) {
        super(TYPE_ID);
        this.clientEventID = clientEventID;
        this.state = state;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientEventID);
        outBuffer.putInt(state.ordinal());
    }

    /**
     * Returns the ID of the client event that is to have its state changed.
     *
     * @return ID of the client event that is to have its state changed.
     */
    public int getClientEventID() {
        return clientEventID;
    }

    /**
     * Returns the double word containing the state (one member of SIMCONNECT_STATE).
     *
     * @return Double word containing the state (one member of SIMCONNECT_STATE).
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
                ", clientEventID=" + clientEventID +
                ", state=" + state +
                "}";
    }
}