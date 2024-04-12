package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_AddToClientDataDefinition function is used to add an offset and a size in bytes, or a type, to a
 * client data definition.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToClientDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToClientDataDefinition.htm</a>
 */
public class AddToClientDataDefinitionRequest extends SimRequest {

    /**
     * Automatically compute offset of the ClientData variable.
     */
    public static final int CLIENTDATAOFFSET_AUTO = -1;

    /**
     * 8-bit integer number.
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT8 = -1;   //  8-bit integer number

    /**
     * 16-bit integer number.
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT16 = -2;   // 16-bit integer number

    /**
     * 32-bit integer number.
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT32 = -3;   // 32-bit integer number

    /**
     * 64-bit integer number.
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT64 = -4;   // 64-bit integer number

    /**
     * 32-bit floating-point number (float).
     */
    public static int SIMCONNECT_CLIENTDATATYPE_FLOAT32 = -5;   // 32-bit floating-point number (float)

    /**
     * 64-bit floating-point number (double).
     */
    public static int SIMCONNECT_CLIENTDATATYPE_FLOAT64 = -6;   // 64-bit floating-point number (double)

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000039;

    private final int defineID;

    private final int offset;

    private final int sizeOrType;

    private final float epsilon;

    private final int datumID;

    AddToClientDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        offset = buffer.getInt();
        sizeOrType = buffer.getInt();
        epsilon = buffer.getFloat();
        datumID = buffer.getInt();
    }

    /**
     * The SimConnect_AddToClientDataDefinition function is used to add an offset and a size in bytes, or a type, to a
     * client data definition.
     *
     * @param defineID   Specifies the ID of the client-defined client data definition.
     * @param offset     Double word containing the offset into the client area, where the new addition is to start.
     *                   Set this to SIMCONNECT_CLIENTDATAOFFSET_AUTO for the offsets to be calculated by the SimConnect
     *                   server.
     * @param sizeOrType Double word containing either the size of the client data in bytes, or one of the constant
     *                   values defined in the table below (note that these definitions have a negative value, all
     *                   positive values will be treated as a size parameter).
     * @param epsilon    If data is requested only when it changes (see the flags parameter of
     *                   SimConnect_RequestClientData, a change will only be reported if it is greater than the value of
     *                   this parameter (not greater than or equal to). The default is zero, so even the tiniest change
     *                   will initiate the transmission of data. Set this value appropriately so insignificant changes
     *                   are not transmitted. This can be used with integer data, the floating point fEpsilon value is
     *                   first truncated to its integer component before the comparison is made (for example, an
     *                   fEpsilon value of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission,
     *                   and a change of 3 will do so). This parameter only applies if one of the six constant values
     *                   listed above has been set in the dwSizeOrType parameter, if a size has been specified
     *                   SimConnect has no record of the type of data being sent, so cannot do a meaningful comparison
     *                   of values.
     * @param datumID    Specifies a client defined datum ID. The default is zero. Use this to identify the data
     *                   received if the data is being returned in tagged format (see the flags parameter of
     *                   SimConnect_RequestClientData. There is no need to specify datum IDs if the data is not being
     *                   returned in tagged format.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToClientDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToClientDataDefinition.htm</a>
     */
    public AddToClientDataDefinitionRequest(int defineID, int offset, int sizeOrType, float epsilon, int datumID) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.offset = offset;
        this.sizeOrType = sizeOrType;
        this.epsilon = epsilon;
        this.datumID = datumID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(offset);
        outBuffer.putInt(sizeOrType);
        outBuffer.putFloat(epsilon);
        outBuffer.putInt(datumID);
    }

    /**
     * Returns the ID of the client-defined client data definition.
     *
     * @return ID of the client-defined client data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the offset into the client area, where the new addition is to start. Set this to
     * SIMCONNECT_CLIENTDATAOFFSET_AUTO for the offsets to be calculated by the SimConnect server.
     *
     * @return Offset into the client area.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Returns either the size of the client data in bytes, or one of the constant values defined in the table below
     * (note that these definitions have a negative value, all positive values will be treated as a size parameter).
     *
     * @return size of the client data in bytes, or one of the type constants.
     */
    public int getSizeOrType() {
        return sizeOrType;
    }

    /**
     * If data is requested only when it changes (see the flags parameter of SimConnect_RequestClientData, a change will
     * only be reported if it is greater than the value of this parameter (not greater than or equal to). The default is
     * zero, so even the tiniest change will initiate the transmission of data. Set this value appropriately so
     * insignificant changes are not transmitted. This can be used with integer data, the floating point fEpsilon value
     * is first truncated to its integer component before the comparison is made (for example, an fEpsilon value of 2.9
     * truncates to 2, so a data change of 2 will not trigger a transmission, and a change of 3 will do so). This
     * parameter only applies if one of the six constant values listed above has been set in the dwSizeOrType parameter,
     * if a size has been specified SimConnect has no record of the type of data being sent, so cannot do a meaningful
     * comparison of values.
     *
     * @return Epsilon value.
     */
    public float getEpsilon() {
        return epsilon;
    }

    /**
     * Returns the client defined datum ID. The default is zero. Use this to identify the data received if the data is
     * being returned in tagged format (see the flags parameter of SimConnect_RequestClientData. There is no need to
     * specify datum IDs if the data is not being returned in tagged format.
     *
     * @return Client defined datum ID.
     */
    public int getDatumID() {
        return datumID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                ", offset=" + offset +
                ", sizeOrType=" + sizeOrType +
                ", epsilon=" + epsilon +
                ", datumID=" + datumID +
                '}';
    }
}