package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_RESERVED_KEY structure is used with the SimConnect_RequestReservedKey function to return the reserved key combination.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_RESERVED_KEY.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_RESERVED_KEY.htm</a>
 */
public class RecvReservedKeyResponse extends SimResponse {

    private final String choiceReserved;

    private final String reservedKey;

    RecvReservedKeyResponse(ByteBuffer buffer) {
        super(buffer);
        choiceReserved = SimUtil.readString(buffer, 30);
        reservedKey = SimUtil.readString(buffer, 50);
    }

    /**
     * Returns the string containing the key that has been reserved. This will be identical to the string entered as one
     * of the choices for the SimConnect_RequestReservedKey function.
     *
     * @return key that has been reserved.
     */
    public String getChoiceReserved() {
        return choiceReserved;
    }

    /**
     * Returns the string containing the reserved key combination. This will be an uppercase string containing all the
     * modifiers that apply. For example, if the client program requests "q", and the choice is accepted, then this
     * parameter will contain "TAB+Q". If the client program requests "Q", then this parameter will contain
     * "SHIFT+TAB+Q". This string could then appear, for example, in a dialog from the client application, informing a
     * user of the appropriate help key.
     *
     * @return The reserved key combination.
     */
    public String getReservedKey() {
        return reservedKey;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "choiceReserved='" + choiceReserved + '\'' +
                ", reservedKey='" + reservedKey + '\'' +
                '}';
    }
}