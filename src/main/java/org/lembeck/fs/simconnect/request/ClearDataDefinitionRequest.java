package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_ClearDataDefinition function is used to remove all simulation variables from a client defined data
 * definition.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm</a>
 */
public class ClearDataDefinitionRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000d;

    private final int dataDefinitionID;

    ClearDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        dataDefinitionID = buffer.getInt();
    }

    /**
     * The SimConnect_ClearDataDefinition function is used to remove all simulation variables from a client defined data
     * definition.
     *
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm</a>
     */
    public ClearDataDefinitionRequest(int dataDefinitionID) {
        super(TYPE_ID);
        this.dataDefinitionID = dataDefinitionID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataDefinitionID);
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataDefinitionID=" + dataDefinitionID +
                "}";
    }
}