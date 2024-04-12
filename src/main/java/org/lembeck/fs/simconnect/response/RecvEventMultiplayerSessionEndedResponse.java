package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT_MULTIPLAYER_SESSION_ENDED structure is sent to a client when they have requested to leave
 * a race, or to all players when the session is terminated by the host.
 */
public class RecvEventMultiplayerSessionEndedResponse extends RecvEventResponse {

    RecvEventMultiplayerSessionEndedResponse(ByteBuffer buffer) {
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