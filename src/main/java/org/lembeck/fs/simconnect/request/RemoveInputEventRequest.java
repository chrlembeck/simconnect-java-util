package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RemoveInputEvent function is used to remove an input event from a specified input group object.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm</a>
 */
public class RemoveInputEventRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000013;

    private final int groupID;

    private final String inputDefinition;

    RemoveInputEventRequest(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        inputDefinition = SimUtil.readString(buffer, 256);
    }

    /**
     * The SimConnect_RemoveInputEvent function is used to remove an input event from a specified input group object.
     *
     * @param groupID         Specifies the ID of the client defined input group from which the event is to be removed.
     * @param inputDefinition String containing the input definition.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm</a>
     */
    public RemoveInputEventRequest(int groupID, String inputDefinition) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.inputDefinition = inputDefinition;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        SimUtil.writeString(outBuffer, inputDefinition, 256);
    }

    /**
     * Returns the ID of the client defined input group from which the event is to be removed.
     *
     * @return ID of the client defined input group from which the event is to be removed.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns the string containing the input definition.
     *
     * @return A string containing the input definition.
     */
    public String getInputDefinition() {
        return inputDefinition;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", groupID=" + groupID +
                ", inputDefinition='" + inputDefinition + "'" +
                "}";
    }
}