package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_SetInputGroupPriority function is used to set the priority for a specified input group object.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm</a>
 */
public class SetInputGroupPriorityRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000012;

    private final int groupID;
    private final Priority priority;

    SetInputGroupPriorityRequest(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        priority = new Priority(buffer.getInt());
    }

    /**
     * The SimConnect_SetInputGroupPriority function is used to set the priority for a specified input group object.
     *
     * @param groupID  Specifies the ID of the client defined input group that the priority setting is to apply to.
     * @param priority Specifies the priority setting for the input group. See the explanation of SimConnect Priorities.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm</a>
     */
    public SetInputGroupPriorityRequest(int groupID, Priority priority) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.priority = priority;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        outBuffer.putInt(priority.getPriorityValue());
    }

    /**
     * Returns the ID of the client defined input group that the priority setting is to apply to.
     *
     * @return ID of the client defined input group that the priority setting is to apply to.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns the priority setting for the input group. See the explanation of SimConnect Priorities.
     *
     * @return The priority setting for the input group. See the explanation of SimConnect Priorities.
     */
    public Priority getPriority() {
        return priority;
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
                ", priority=" + priority +
                "}";
    }
}