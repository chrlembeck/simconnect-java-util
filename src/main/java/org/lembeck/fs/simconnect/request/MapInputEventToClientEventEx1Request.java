package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

public class MapInputEventToClientEventEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000004d;

    private final int groupID;
    private final String inputDefinition;
    private final int downEventID;
    private final int downValue;
    private final int upEventID;
    private final int upValue;
    private final boolean maskable;

    MapInputEventToClientEventEx1Request(ByteBuffer buffer) {
        super(buffer);
        groupID = buffer.getInt();
        inputDefinition = SimUtil.readString(buffer, 256);
        downEventID = buffer.getInt();
        downValue = buffer.getInt();
        upEventID = buffer.getInt();
        upValue = buffer.getInt();
        maskable = buffer.getInt() != 0;
    }

    public MapInputEventToClientEventEx1Request(int groupID, String inputDefinition, int downEventID, int downValue, int upEventID, int upValue, boolean maskable) {
        super(TYPE_ID);
        this.groupID = groupID;
        this.inputDefinition = inputDefinition;
        this.downEventID = downEventID;
        this.downValue = downValue;
        this.upEventID = upEventID;
        this.upValue = upValue;
        this.maskable = maskable;
    }


    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(groupID);
        SimUtil.writeString(outBuffer, inputDefinition, 256);
        outBuffer.putInt(downEventID);
        outBuffer.putInt(downValue);
        outBuffer.putInt(upEventID);
        outBuffer.putInt(upValue);
        outBuffer.putInt(maskable ? 1 : 0);
    }

    public int getGroupID() {
        return groupID;
    }

    public String getInputDefinition() {
        return inputDefinition;
    }

    public int getDownEventID() {
        return downEventID;
    }

    public int getDownValue() {
        return downValue;
    }

    public int getUpEventID() {
        return upEventID;
    }

    public int getUpValue() {
        return upValue;
    }

    public boolean isMaskable() {
        return maskable;
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
                ", groupID='" + groupID + "'" +
                ", inputDefinition='" + inputDefinition + "'" +
                ", downEventID=" + downEventID +
                ", downValue=" + downValue +
                ", upEventID=" + upEventID +
                ", upValue=" + upValue +
                ", maskable=" + maskable +
                "}";
    }
}