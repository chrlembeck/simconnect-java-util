package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_LIST_TEMPLATE structure is used to provide information on the number of elements in a list
 * returned to the client, and the number of packets that were used to transmit the data.
 */
public class RecvListTemplate extends SimResponse {

    /**
     * Double word containing the client defined request ID.
     */
    protected final int requestID;

    /**
     * Double word containing the number of elements in the list that are within this packet. For example, if there are
     * items returned in the structure, then this field will contain 25, but if there are 400 items in the list and the
     * data is returned in two packets, then this value will contain the number of entries within each packet, ie: 200.
     */
    protected final int arraySize;

    /**
     * Double word containing the index number of this list packet. This number will be from 0 to dwOutOf - 1.
     */
    protected final int entryNumber;

    /**
     * Double word containing the total number of packets used to transmit the list.
     */
    protected final int outOf;

    RecvListTemplate(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        arraySize = buffer.getInt();
        entryNumber = buffer.getInt();
        outOf = buffer.getInt();
    }

    /**
     * Returns the client defined request ID.
     *
     * @return The client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the number of elements in the list that are within this packet. For example, if there are items returned
     * in the structure, then this field will contain 25, but if there are 400 items in the list and the data is
     * returned in two packets, then this value will contain the number of entries within each packet, ie: 200.
     *
     * @return Number of elements in the list that are within this packet.
     */
    public int getArraySize() {
        return arraySize;
    }

    /**
     * Returns the index number of this list packet. This number will be from 0 to dwOutOf - 1.
     *
     * @return Index number of this list packet.
     */
    public int getEntryNumber() {
        return entryNumber;
    }

    /**
     * Returns the total number of packets used to transmit the list.
     *
     * @return Total number of packets used to transmit the list.
     */
    public int getOutOf() {
        return outOf;
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
                ", arraySize=" + arraySize +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                '}';
    }
}