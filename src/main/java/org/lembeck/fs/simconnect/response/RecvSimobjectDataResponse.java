package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

/**
 * The SIMCONNECT_RECV_SIMOBJECT_DATA structure will be received by the client after a successful call to
 * SimConnect_RequestDataOnSimObject or SimConnect_RequestDataOnSimObjectType.
 */
public class RecvSimobjectDataResponse extends SimResponse {

    /**
     * The default, data will be sent strictly according to the defined period.
     */
    public static final int DATA_REQUEST_FLAG_DEFAULT = 0;

    /**
     * Data will only be sent to the client when one or more values have changed. If this is the only flag set, then all
     * the variables in a data definition will be returned if just one of the values changes.
     */
    public static final int DATA_REQUEST_FLAG_CHANGED = 1;

    /**
     * Requested data will be sent in tagged format (datum ID/value pairs). Tagged format requires that a datum
     * reference ID is returned along with the data value, in order that the client code is able to identify the
     * variable. This flag is usually set in conjunction with the previous flag, but it can be used on its own to return
     * all the values in a data definition in datum ID/value pairs. See the SIMCONNECT_RECV_SIMOBJECT_DATA structure for
     * more details.
     */
    public static final int SIMCONNECT_DATA_REQUEST_FLAG_TAGGED = 2;

    private final int requestID;

    private final int objectID;

    private final int defineID;

    private final int flags;

    private final int entryNumber;

    private final int outOf;

    private final int defineCount;

    private final byte[] data;

    RecvSimobjectDataResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        objectID = buffer.getInt();
        defineID = buffer.getInt();
        flags = buffer.getInt();
        entryNumber = buffer.getInt();
        outOf = buffer.getInt();
        defineCount = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    /**
     * Returns the ID of the client defined request.
     *
     * @return ID of the client defined request.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * returns the ID of the client defined object.
     *
     * @return ID of the client defined object.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the flags that were set for this data request, see SimConnect_RequestDataOnSimObject for a description
     * of the flags. This parameter will always be set to zero if the call was SimConnect_RequestDataOnSimObjectType.
     *
     * @return Flags that were set for this data request.
     */
    public int getFlags() {
        return flags;
    }

    /**
     * If multiple objects are being returned, this is the index number of this object out of a total of outof.
     * This will always be 1 if the call was SimConnect_RequestDataOnSimObject, and can be 0 or more if the call was
     * SimConnect_RequestDataOnSimObjectType.
     *
     * @return Index number of this object.
     * @see #getOutOf()
     */
    public int getEntryNumber() {
        return entryNumber;
    }

    /**
     * The total number of objects being returned. Note that entryNumber and outOf start with 1 not 0, so if two
     * objects are being returned entryNumber and outOf pairs will be 1,2 and 2,2 for the two objects. This will
     * always be 1 if the call was SimConnect_RequestDataOnSimObject, and can be 0 or more if the call was
     * SimConnect_RequestDataOnSimObjectType.
     *
     * @return The total number of objects being returned.
     * @see #getEntryNumber()
     */
    public int getOutOf() {
        return outOf;
    }

    /**
     * The number of 8-byte elements in the dwData array.
     *
     * @return Number of 8-byte elements in the dwData array.
     */
    public int getDefineCount() {
        return defineCount;
    }

    /**
     * A data array containing information on a specified object in 8-byte (double word) elements wrapped as ByteBuffer.
     * The length of the array is defineCount.
     *
     * @return ByteBuffer holding the data of the response.
     * @see #getDefineCount()
     */
    public ByteBuffer getData() {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(LITTLE_ENDIAN);
        return buffer;
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
                ", requestID=" + requestID +
                ", objectID=" + objectID +
                ", defineID=" + defineID +
                ", flags=" + flags +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                ", defineCount=" + defineCount +
                ", data=" + SimUtil.byteArrayToString(data) +
                "}";
    }
}