package org.lembeck.fs.simconnect.request;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearDataDefinitionRequestTest {

    @Test
    public void newClearDataDefinintionRequest() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(20);
        buffer.putInt(47);
        buffer.putInt(0xf000000d);
        buffer.putInt(87);
        buffer.putInt(113);
        buffer.position(0);
        ClearDataDefinitionRequest req = new ClearDataDefinitionRequest(buffer);
        assertEquals(20, req.getSize());
        assertEquals(47, req.getVersion());
        assertEquals(0xf000000d, req.getTypeID());
        assertEquals(87, req.getIdentifier());
        assertEquals(113, req.getDataDefinitionID());
    }


}