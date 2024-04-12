package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SimConnect_SetClientData function is used to write one or more units of data to a client data area.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetClientData.htm</a>
 */
public class SetClientDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000003c;

    private final int clientDataID;

    private final int defineID;

    private final boolean tagged;

    private final int reserved;

    private final int dataSize;

    private final byte[] data;

    SetClientDataRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        defineID = buffer.getInt();
        tagged = buffer.getInt() == 1;
        reserved = buffer.getInt();
        dataSize = buffer.getInt();
        data = new byte[dataSize];
        buffer.get(data);
    }

    /**
     * The SimConnect_SetClientData function is used to write one or more units of data to a client data area.
     *
     * @param clientDataID Specifies the ID of the client data area.
     * @param defineID     Specifies the ID of the client defined client data definition.
     * @param tagged       Whether the data is in tagged format or default.
     * @param reserved     Reserved for future use. Set to zero.
     * @param dataSize     Specifies the size of the data set in bytes. The server will check that this size matches
     *                     exactly the size of the data definition provided in the DefineID parameter. An exception will
     *                     be returned if this is not the case.
     * @param data         Pointer to the data that is to be written. If the data is not in tagged format, this should
     *                     point to the block of client data. If the data is in tagged format this should point to the
     *                     first tag name (datumID), which is always four bytes long, which should be followed by the
     *                     data itself. Any number of tag name/value pairs can be specified this way, the server will
     *                     use the cbUnitSize parameter to determine how much data has been sent.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetClientData.htm</a>
     */
    public SetClientDataRequest(int clientDataID, int defineID, boolean tagged, int reserved, int dataSize,
            byte[] data) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.defineID = defineID;
        this.tagged = tagged;
        this.reserved = reserved;
        this.dataSize = dataSize;
        this.data = data;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(tagged ? 1 : 0);
        outBuffer.putInt(reserved);
        outBuffer.putInt(dataSize);
        outBuffer.put(data);
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
     * Returns the ID of the client defined client data definition.
     *
     * @return ID of the client defined client data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns whether the data is in tagged format or default.
     *
     * @return True means tagged format, false means default.
     */
    public boolean isTagged() {
        return tagged;
    }

    /**
     * Reserved for future use.
     *
     * @return Reserved for future use.
     */
    public int getReserved() {
        return reserved;
    }

    /**
     * Returns the size of the data set in bytes. The server will check that this size matches exactly the size of the
     * data definition provided in the DefineID parameter. An exception will be returned if this is not the case.
     *
     * @return Size of the data set in bytes.
     */
    public int getDataSize() {
        return dataSize;
    }

    /**
     * Pointer to the data that is to be written. If the data is not in tagged format, this should point to the block of
     * client data. If the data is in tagged format this should point to the first tag name (datumID), which is always
     * four bytes long, which should be followed by the data itself. Any number of tag name/value pairs can be specified
     * this way, the server will use the cbUnitSize parameter to determine how much data has been sent.
     *
     * @return Data that is to be written.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "clientDataID=" + clientDataID +
                ", defineID=" + defineID +
                ", tagged=" + tagged +
                ", reserved=" + reserved +
                ", dataSize=" + dataSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}