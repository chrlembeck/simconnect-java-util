package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_FACILITY_DATA_END structure is used to signify the end of a data stream from the server after a call to SimConnect_RequestFacilityData.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITY_DATA_END.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_FACILITY_DATA_END.htm</a>
 */
public class RecvFacilityDataEndResponse extends SimResponse {

    private final int requestID;

    RecvFacilityDataEndResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
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
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                '}';
    }
}