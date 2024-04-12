package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT_MULTIPLAYER_SERVER_STARTED structure is sent to the host when the session is visible to
 * other users in the lobby.
 */
public class RecvEventMultiplayerServerStartedResponse extends RecvEventResponse {

    RecvEventMultiplayerServerStartedResponse(ByteBuffer buffer) {
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