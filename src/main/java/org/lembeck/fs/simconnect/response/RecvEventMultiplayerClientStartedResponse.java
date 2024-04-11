package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;


/**
 * The SIMCONNECT_RECV_EVENT_MULTIPLAYER_CLIENT_STARTED structure is sent to a client when they have successfully joined
 * a multi-player race.
 */
public class RecvEventMultiplayerClientStartedResponse extends RecvEventResponse {

    RecvEventMultiplayerClientStartedResponse(ByteBuffer buffer) {
        super(buffer);
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
                "}";
    }
}