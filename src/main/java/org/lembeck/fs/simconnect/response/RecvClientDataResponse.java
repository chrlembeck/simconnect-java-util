package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;

public class RecvClientDataResponse extends RecvSimobjectDataResponse {

    RecvClientDataResponse(ByteBuffer buffer) {
        super(buffer);
    }
}