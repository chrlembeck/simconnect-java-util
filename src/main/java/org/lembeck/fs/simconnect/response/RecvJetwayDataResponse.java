package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_JETWAY_DATA structure is used to return a list of SIMCONNECT_JETWAY_DATA structures.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_JETWAY_DATA.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_JETWAY_DATA.htm</a>
 */
public class RecvJetwayDataResponse extends RecvListTemplate {

    private final JetwayData[] jetways;

    RecvJetwayDataResponse(ByteBuffer buffer) {
        super(buffer);
        jetways = new JetwayData[getArraySize()];
        for (int i = 0; i < getArraySize(); i++) {
            jetways[i] = new JetwayData(buffer);
        }
    }

    /**
     * Returns the Array of SIMCONNECT_JETWAY_DATA structures.
     *
     * @return Array of SIMCONNECT_JETWAY_DATA structures.
     */
    public JetwayData[] getJetways() {
        return jetways;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
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