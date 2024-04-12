package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_MapClientDataNameToID function is used to associate an ID with a named client data area.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientDataNameToID.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientDataNameToID.htm</a>
 */
public class MapClientDataNameToIDRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000037;

    private final String clientDataName;

    private final int clientDataID;

    MapClientDataNameToIDRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataName = SimUtil.readString(buffer, 256);
        clientDataID = buffer.getInt();
    }

    /**
     * The SimConnect_MapClientDataNameToID function is used to associate an ID with a named client data area.
     *
     * @param clientDataName String containing the client data area name. This is the name that another client will use
     *                       to specify the data area. The name is not case-sensitive. If the name requested is already
     *                       in use by another addon, a error will be returned.
     * @param clientDataID   A unique ID for the client data area, specified by the client. If the ID number is already
     *                       in use, an error will be returned.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientDataNameToID.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientDataNameToID.htm</a>
     */
    public MapClientDataNameToIDRequest(String clientDataName, int clientDataID) {
        super(TYPE_ID);
        this.clientDataName = clientDataName;
        this.clientDataID = clientDataID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, clientDataName, 256);
        outBuffer.putInt(clientDataID);
    }

    /**
     * Returns the string containing the client data area name. This is the name that another client will use to specify
     * the data area. The name is not case-sensitive. If the name requested is already in use by another addon, an error
     * will be returned.
     *
     * @return String containing the client data area name.
     */
    public String getClientDataName() {
        return clientDataName;
    }

    /**
     * Returns a unique ID for the client data area, specified by the client. If the ID number is already in use, an
     * error will be returned.
     *
     * @return ID for the client data area.
     */
    public int getClientDataID() {
        return clientDataID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "MapClientDataNameToIDRequest{" +
                "clientDataName='" + clientDataName + '\'' +
                ", clientDataID=" + clientDataID +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}