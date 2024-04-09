package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

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

    public int getGroupID() {
        return groupID;
    }

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