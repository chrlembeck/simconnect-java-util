package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_MapInputEventToClientEvent_EX1 function is used to connect input events (such as keystrokes,
 * joystick or mouse movements) with the sending of appropriate event notifications.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_MapInputEventToClientEvent_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_MapInputEventToClientEvent_EX1.htm</a>
 */
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

    /**
     * The SimConnect_MapInputEventToClientEvent_EX1 function is used to connect input events (such as keystrokes,
     * joystick or mouse movements) with the sending of appropriate event notifications.
     *
     * @param groupID         Specifies the ID of the client defined input group that the input event is to be added to.
     * @param inputDefinition String containing the definition of the input events (keyboard keys, mouse or joystick
     *                        events, for example). See the remarks and example for a range of possibilities.
     * @param downEventID     Specifies the ID of the down, and default, event. This is the client defined event that is
     *                        triggered when the input event occurs. If only an up event is required, set this to
     *                        SIMCONNECT_UNUSED.
     * @param downValue       Specifies an optional numeric value, which will be returned when the down event occurs.
     * @param upEventID       Specifies the ID of the up event. This is the client defined event that is triggered when
     *                        the up action occurs.
     * @param upValue         Specifies an optional numeric value, which will be returned when the up event occurs.
     * @param maskable        If set to true, specifies that the client will mask the event, and no other lower priority
     *                        clients will receive it. The default is false.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_MapInputEventToClientEvent_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_MapInputEventToClientEvent_EX1.htm</a>
     */
    public MapInputEventToClientEventEx1Request(int groupID, String inputDefinition, int downEventID, int downValue,
            int upEventID, int upValue, boolean maskable) {
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

    /**
     * Returns the ID of the client defined input group that the input event is to be added to.
     *
     * @return ID of the client defined input group that the input event is to be added to.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Returns the string containing the definition of the input events (keyboard keys, mouse or joystick events, for
     * example). See the remarks and example for a range of possibilities.
     *
     * @return String containing the definition of the input events.
     */
    public String getInputDefinition() {
        return inputDefinition;
    }

    /**
     * Returns the ID of the down, and default, event. This is the client defined event that is triggered when the input
     * event occurs. If only an up event is required, set this to SIMCONNECT_UNUSED.
     *
     * @return ID of the down, and default, event.
     */
    public int getDownEventID() {
        return downEventID;
    }

    /**
     * Returns the optional numeric value, which will be returned when the down event occurs.
     *
     * @return The optional numeric value, which will be returned when the down event occurs.
     */
    public int getDownValue() {
        return downValue;
    }

    /**
     * Returns the ID of the up event. This is the client defined event that is triggered when the up action occurs.
     *
     * @return ID of the up event.
     */
    public int getUpEventID() {
        return upEventID;
    }

    /**
     * Returns the optional numeric value, which will be returned when the up event occurs.
     *
     * @return Numeric value, which will be returned when the up event occurs.
     */
    public int getUpValue() {
        return upValue;
    }

    /**
     * If set to true, specifies that the client will mask the event, and no other lower priority clients will receive it. The default is false.
     *
     * @return true means masked, false not (default).
     */
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