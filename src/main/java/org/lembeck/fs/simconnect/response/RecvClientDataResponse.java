package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;


/**
 * The SIMCONNECT_RECV_CLIENT_DATA structure will be received by the client after a successful call to
 * SimConnect_RequestClientData. It is an identical structure to SIMCONNECT_RECV_SIMOBJECT_DATA.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_CLIENT_DATA.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_CLIENT_DATA.htm</a>
 */
public class RecvClientDataResponse extends RecvSimobjectDataResponse {

    RecvClientDataResponse(ByteBuffer buffer) {
        super(buffer);
    }
}