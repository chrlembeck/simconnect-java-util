package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_ClearInputGroup function is used to remove all the input events from a specified input group object.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm</a>
 */
public class ClearInputGroupRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000014;

    private final int groupID;

    ClearInputGroupRequest(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
    }

    /**
     * The SimConnect_ClearInputGroup function is used to remove all the input events from a specified input group object.
     *
     * @param groupID Specifies the ID of the client defined input group that is to have all its events removed.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm</a>
     */
    public ClearInputGroupRequest(int groupID) {
        super(TYPE_ID);
        this.groupID = groupID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
    }

    /**
     * Returns the ID of the client defined input group that is to have all its events removed.
     *
     * @return ID of the client defined input group that is to have all its events removed.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "groupID=" + groupID +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}