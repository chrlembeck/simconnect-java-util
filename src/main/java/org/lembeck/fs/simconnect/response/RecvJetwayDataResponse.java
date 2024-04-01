package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvJetwayDataResponse extends RecvListTemplate {

    private final JetwayData[] jetways;

    RecvJetwayDataResponse(ByteBuffer buffer) {
        super(buffer);
        jetways = new JetwayData[getArraySize()];
        for (int i = 0; i < getArraySize(); i++) {
            jetways[i] = new JetwayData(buffer);
        }
    }

    public JetwayData[] getJetways() {
        return jetways;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "jetways=" + Arrays.toString(jetways) +
                ", requestID=" + requestID +
                ", arraySize=" + arraySize +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                ", size=" + size +
                ", version=" + version +
                ", typeId=" + typeId +
                '}';
    }
}