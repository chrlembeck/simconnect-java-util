package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class RecvReservedKeyResponse extends SimResponse {

    private final String choiceReserved;

    private final String reservedKey;

    RecvReservedKeyResponse(ByteBuffer buffer) {
        super(buffer);
        choiceReserved = SimUtil.readString(buffer, 30);
        reservedKey = SimUtil.readString(buffer, 50);
    }

    public String getChoiceReserved() {
        return choiceReserved;
    }

    public String getReservedKey() {
        return reservedKey;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "choiceReserved='" + choiceReserved + '\'' +
                ", reservedKey='" + reservedKey + '\'' +
                '}';
    }
}