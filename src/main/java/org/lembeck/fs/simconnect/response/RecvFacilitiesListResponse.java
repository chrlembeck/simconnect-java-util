package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

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

    public int getRequestID() {
        return requestID;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public int getOutOf() {
        return outOf;
    }
}