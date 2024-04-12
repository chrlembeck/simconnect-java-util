package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_CreateClientData function is used to request the creation of a reserved data area for this client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_CreateClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_CreateClientData.htm</a>
 */
public class CreateClientDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000038;

    private final int clientDataID;

    private final int dataSize;

    private final boolean readonly;

    CreateClientDataRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        dataSize = buffer.getInt();
        readonly = buffer.getInt() != 0;
    }

    /**
     * The SimConnect_CreateClientData function is used to request the creation of a reserved data area for this client.
     *
     * @param clientDataID ID of the client data area. Before calling this function, call
     *                     SimConnect_MapClientDataNameToID to map an ID to a unique client area name.
     * @param dataSize     Double word containing the size of the data area in bytes.
     * @param readonly     Specifies whether the data area can only be written to by this client (the client creating
     *                     the data area). By default, other clients can write to this data area.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_CreateClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_CreateClientData.htm</a>
     */
    public CreateClientDataRequest(int clientDataID, int dataSize, boolean readonly) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.dataSize = dataSize;
        this.readonly = readonly;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(dataSize);
        outBuffer.putInt(readonly ? 1 : 0);
    }

    /**
     * Returns the ID of the client data area.
     *
     * @return ID of the client data area.
     */
    public int getClientDataID() {
        return clientDataID;
    }

    /**
     * Returns the size of the data area in bytes.
     *
     * @return Size of the data area in bytes.
     */
    public int getDataSize() {
        return dataSize;
    }

    /**
     * Returns whether the data area can only be written to by this client (the client creating the data area).
     * By default, other clients can write to this data area.
     *
     * @return True means, the data are read only, false means writeable.
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "CreateClientDataRequest{" +
                "clientDataID=" + clientDataID +
                ", dataSize=" + dataSize +
                ", readonly=" + readonly +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}