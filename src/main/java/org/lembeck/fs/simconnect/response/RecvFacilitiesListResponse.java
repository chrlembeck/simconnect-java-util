package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_FACILITIES_LIST structure is used to provide information on the number of elements in a list of
 * facilities returned to the client, and the number of packets that were used to transmit the data.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITIES_LIST.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITIES_LIST.htm</a>
 */
public abstract class RecvFacilitiesListResponse extends SimResponse {

    /**
     * Double word containing the client defined request ID.
     */
    protected final int requestID;

    /**
     * Double word containing the number of elements in the list that are within this packet. For example, if there are 25 airports returned in the SIMCONNECT_RECV_AIRPORT_LIST structure, then this field will contain 25, but if there are 400 airports in the list and the data is returned in two packets, then this value will contain the number of entries within each packet.
     */
    protected final int arraySize;

    /**
     * Double word containing the index number of this list packet. This number will be from 0 to outOf - 1.
     */
    protected final int entryNumber;

    /**
     * Double word containing the total number of packets used to transmit the list.
     */
    protected final int outOf;

    RecvFacilitiesListResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        arraySize = buffer.getInt();
        entryNumber = buffer.getInt();
        outOf = buffer.getInt();
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
     * Returns the number of elements in the list that are within this packet. For example, if there are 25 airports
     * returned in the SIMCONNECT_RECV_AIRPORT_LIST structure, then this field will contain 25, but if there are 400
     * airports in the list and the data is returned in two packets, then this value will contain the number of entries
     * within each packet.
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
}