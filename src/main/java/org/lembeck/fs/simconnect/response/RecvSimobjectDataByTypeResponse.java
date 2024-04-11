package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure will be received by the client after a successful call to
 * SimConnect_RequestDataOnSimObjectType. It is an identical structure to SIMCONNECT_RECV_SIMOBJECT_DATA.
 */
public class RecvSimobjectDataByTypeResponse extends RecvSimobjectDataResponse {

    RecvSimobjectDataByTypeResponse(ByteBuffer buffer) {
        super(buffer);
    }
}