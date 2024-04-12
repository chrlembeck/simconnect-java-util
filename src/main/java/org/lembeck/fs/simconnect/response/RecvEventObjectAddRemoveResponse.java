package org.lembeck.fs.simconnect.response;


import org.lembeck.fs.simconnect.constants.SimObjectType;
import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT_OBJECT_ADDREMOVE structure is used to return the type and ID of an AI object that has been
 * added or removed from the simulation, by any client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_OBJECT_ADDREMOVE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_OBJECT_ADDREMOVE.htm</a>
 */
public class RecvEventObjectAddRemoveResponse extends RecvEventResponse {

    private final SimObjectType type;

    RecvEventObjectAddRemoveResponse(ByteBuffer buffer) {
        super(buffer);
        type = SimObjectType.ofId(buffer.getInt());
    }

    /**
     * Returns the type of object that was added or removed. One member of the SIMCONNECT_SIMOBJECT_TYPE enumeration.
     *
     * @return Type of the object.
     */
    public SimObjectType getType() {
        return type;
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
                ", groupID=" + (groupID == UNKNOWN_GROUP ? "UNKNOWN_GROUP" : groupID) +
                ", eventID=" + eventID +
                ", data=" + data +
                ", type=" + type +
                "}";
    }
}