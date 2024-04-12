package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestReservedKey function is used to request a specific keyboard TAB-key combination applies
 * only to this client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm</a>
 */
public class RequestReservedKeyRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000016;

    private final int eventID;

    private final String keyChoice1;

    private final String keyChoice2;

    private final String keyChoice3;

    RequestReservedKeyRequest(ByteBuffer buffer) {
        super(buffer);
        eventID = buffer.getInt();
        keyChoice1 = SimUtil.readString(buffer, 30);
        keyChoice2 = SimUtil.readString(buffer, 30);
        keyChoice3 = SimUtil.readString(buffer, 30);
    }

    /**
     * The SimConnect_RequestReservedKey function is used to request a specific keyboard TAB-key combination applies
     * only to this client.
     *
     * @param eventID    Specifies the client defined event ID.
     * @param keyChoice1 String containing the first key choice. Refer to the list below for all the choices that can
     *                   be entered for these three parameters.
     * @param keyChoice2 String containing the second key choice.
     * @param keyChoice3 String containing the third key choice.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm</a>
     */
    public RequestReservedKeyRequest(int eventID, String keyChoice1, String keyChoice2, String keyChoice3) {
        super(TYPE_ID);
        this.eventID = eventID;
        this.keyChoice1 = keyChoice1;
        this.keyChoice2 = keyChoice2;
        this.keyChoice3 = keyChoice3;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(eventID);
        SimUtil.writeString(outBuffer, keyChoice1, 30);
        SimUtil.writeString(outBuffer, keyChoice2, 30);
        SimUtil.writeString(outBuffer, keyChoice3, 30);
    }

    /**
     * Returns the client defined event ID.
     *
     * @return Client defined event ID.
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Returns the string containing the first key choice. Refer to the list below for all the choices that can be
     * entered for these three parameters.
     *
     * @return String containing the first key choice.
     */
    public String getKeyChoice1() {
        return keyChoice1;
    }

    /**
     * Returns the string containing the second key choice.
     *
     * @return String containing the second key choice.
     */
    public String getKeyChoice2() {
        return keyChoice2;
    }

    /**
     * Returns the string containing the third key choice.
     *
     * @return String containing the third key choice.
     */
    public String getKeyChoice3() {
        return keyChoice3;
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
                ", eventID=" + eventID +
                ", keyChoice1'=" + keyChoice1 + "'" +
                ", keyChoice2'=" + keyChoice2 + "'" +
                ", keyChoice3'=" + keyChoice3 + "'" +
                "}";
    }
}