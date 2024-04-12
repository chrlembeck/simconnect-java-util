package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.SimUtil.UNKNOWN_GROUP;

/**
 * The SIMCONNECT_RECV_EVENT_FILENAME structure is used to return a filename and an event ID to the client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_FILENAME.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_EVENT_FILENAME.htm</a>
 */
public class RecvEventFilenameResponse extends RecvEventResponse {

    /**
     * The returned filename.
     */
    private final String filename;

    /**
     * Reserved, should be 0.
     */
    private final int flags;

    RecvEventFilenameResponse(ByteBuffer buffer) {
        super(buffer);
        filename = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        flags = buffer.getInt();
    }

    /**
     * returns the filename.
     *
     * @return Name of the file.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Reserved. Should be 0.
     *
     * @return Reserved value.
     */
    public int getFlags() {
        return flags;
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
                ", filename=" + filename +
                ", flags=" + flags +
                "}";
    }
}