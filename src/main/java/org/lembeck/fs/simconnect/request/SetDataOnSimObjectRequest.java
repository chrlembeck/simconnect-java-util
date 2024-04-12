package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.DataSetFlag;

import java.nio.ByteBuffer;

import static org.lembeck.fs.simconnect.constants.DataSetFlag.NOT_TAGGED;
import static org.lembeck.fs.simconnect.constants.DataSetFlag.TAGGED;

/**
 * The SimConnect_SetDataOnSimObject function is used to make changes to the data properties of an object.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm</a>
 */
public class SetDataOnSimObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000010;

    private final int dataDefinitionID;

    private final int objectID;

    private final DataSetFlag dataSetFlag;

    private final int arrayCount;

    private final int unitSize;

    private final byte[] data;

    SetDataOnSimObjectRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionID = buffer.getInt();
        objectID = buffer.getInt();
        dataSetFlag = buffer.getInt() == 0 ? NOT_TAGGED : TAGGED;
        int arrayCountTemp = buffer.getInt();
        arrayCount = arrayCountTemp == 0 ? 1 : arrayCountTemp;
        unitSize = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    /**
     * The SimConnect_SetDataOnSimObject function is used to make changes to the data properties of an object.
     *
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @param objectID         Specifies the ID of the Microsoft Flight Simulator object that the data should be about.
     *                         This ID can be SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained from
     *                         a SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to
     *                         SimConnect_RequestDataOnSimObjectType. Also refer to the note on "multiplayer mode" at
     *                         the end of the remarks for SimConnect_RequestDataOnSimObject.
     * @param dataSetFlag      Default or tagged format.
     * @param arrayCount       Specifies the number of elements in the data array. A count of zero is interpreted as one
     *                         element. Ensure that the data array has been initialized completely before transmitting it
     *                         to Microsoft Flight Simulator. Failure to properly initialize all array elements may
     *                         result in unexpected behavior.
     * @param unitSize         Specifies the size of each element in the data array in bytes.
     * @param data             Pointer to the data that is to be written. If the data is not in tagged format, this
     *                         should point to the block of data. If the data is in tagged format this should point to
     *                         the first tag name (datumID), which is always four bytes long, which should be followed
     *                         by the data itself. Any number of tag name/value pairs can be specified this way, the
     *                         server will use the cbUnitSize parameter to determine how much data has been sent.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm</a>
     */
    public SetDataOnSimObjectRequest(int dataDefinitionID, int objectID, DataSetFlag dataSetFlag, int arrayCount, int unitSize, byte[] data) {
        super(TYPE_ID);
        this.dataDefinitionID = dataDefinitionID;
        this.objectID = objectID;
        this.dataSetFlag = dataSetFlag;
        this.arrayCount = arrayCount;
        this.unitSize = unitSize;
        this.data = data;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataDefinitionID);
        outBuffer.putInt(objectID);
        outBuffer.putInt(dataSetFlag == TAGGED ? 1 : 0);
        outBuffer.putInt(arrayCount);
        outBuffer.putInt(unitSize);
        outBuffer.put(data);
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    /**
     * Returns the ID of the Microsoft Flight Simulator object that the data should be about. This ID can be
     * SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained from a
     * SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to SimConnect_RequestDataOnSimObjectType.
     * Also refer to the note on "multiplayer mode" at the end of the remarks for SimConnect_RequestDataOnSimObject.
     *
     * @return ID of the Microsoft Flight Simulator object that the data should be about.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Default or tagged format.
     *
     * @return Default or tagged format.
     * @see DataSetFlag
     */
    public DataSetFlag getDataSetFlag() {
        return dataSetFlag;
    }

    /**
     * Returns the number of elements in the data array. A count of zero is interpreted as one element. Ensure that the
     * data array has been initialized completely before transmitting it to Microsoft Flight Simulator. Failure to
     * properly initialize all array elements may result in unexpected behavior.
     *
     * @return Number of elements in the data array.
     */
    public int getArrayCount() {
        return arrayCount;
    }

    /**
     * Returns the size of each element in the data array in bytes.
     *
     * @return Size of each element in the data array in bytes.
     */
    public int getUnitSize() {
        return unitSize;
    }

    /**
     * Returns the data that is to be written. If the data is not in tagged format, this should point to the block of
     * data. If the data is in tagged format this should point to the first tag name (datumID), which is always four
     * bytes long, which should be followed by the data itself. Any number of tag name/value pairs can be specified
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
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataDefinitionID=" + dataDefinitionID +
                ", objectID=" + objectID +
                ", dataSetFlag=" + dataSetFlag +
                ", arrayCount=" + arrayCount +
                ", unitSize=" + unitSize +
                ", data=" + SimUtil.byteArrayToString(data) +
                "}";
    }
}