package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.constants.FacilityDataType;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_FACILITY_DATA structure is used to provide information that has been requested from the server
 * using the SimConnect_RequestFacilityData function. This struct may be received multiple times before receiving
 * SIMCONNECT_RECV_FACILITY_DATA_END.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITY_DATA.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITY_DATA.htm</a>
 */
public class RecvFacilityDataResponse extends SimResponse {

    private final int requestID;

    private final int uniqueRequestID;

    private final int parentUniqueRequestID;

    private final FacilityDataType type;

    private final boolean listItem;

    private final int itemIndex;

    private final int listSize;

    private final byte[] data;

    RecvFacilityDataResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        uniqueRequestID = buffer.getInt();
        parentUniqueRequestID = buffer.getInt();
        type = FacilityDataType.ofId(buffer.getInt());
        listItem = buffer.getInt() != 0;
        itemIndex = buffer.getInt();
        listSize = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    /**
     * Returns the client defined request ID.
     *
     * @return Client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the unique request ID, so the client can identify it.
     *
     * @return Unique request ID.
     */
    public int getUniqueRequestID() {
        return uniqueRequestID;
    }

    /**
     * If the current message is about a child object, this field will contain the parent's UniqueRequestId, otherwise
     * it will be 0.
     *
     * @return Parents unique request id or 0.
     */
    public int getParentUniqueRequestID() {
        return parentUniqueRequestID;
    }

    /**
     * Specifies the type of the object, will be a value from the SIMCONNECT_FACILITY_DATA_TYPE enum.
     *
     * @return Type of the object.
     */
    public FacilityDataType getType() {
        return type;
    }

    /**
     * If the current message is about a child object, this specifies if it is an orphan object or not.
     *
     * @return Specifies if it is an orphan object or not.
     */
    public boolean isListItem() {
        return listItem;
    }

    /**
     * If IsListItem is true then this specifies the index in the list.
     *
     * @return Index in the list (if any).
     */
    public int getItemIndex() {
        return itemIndex;
    }

    /**
     * If IsListItem is true, then this specifies the list size.
     *
     * @return List size (if any).
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * Returns the buffer of data. Have to cast it to a struct which matches the definition.
     *
     * @return Buffer of data.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Returns the buffer of data as ByteBuffer. Have to cast it to a struct which matches the definition.
     *
     * @return Buffer of data as ByteBuffer.
     */
    public ByteBuffer getDataBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                ", uniqueRequestID=" + uniqueRequestID +
                ", parentUniqueRequestID=" + parentUniqueRequestID +
                ", type=" + type +
                ", listItem=" + listItem +
                ", itemIndex=" + itemIndex +
                ", listSize=" + listSize +
                ", data=" + Arrays.toString(data) +
                ", size=" + size +
                ", version=" + version +
                ", typeId=" + typeId +
                '}';
    }
}