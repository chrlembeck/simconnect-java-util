package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AddToFacilityDefinition function is used to create a facility data definition.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm</a>
 */
public class AddToFacilityDefinitionRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000045;

    private final int defineID;

    private final String fieldName;

    AddToFacilityDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        fieldName = SimUtil.readString(buffer, 256);
    }

    /**
     * The SimConnect_AddToFacilityDefinition function is used to create a facility data definition.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param fieldName Specifies the client defined request ID. This will be returned along with the data.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm</a>
     */
    public AddToFacilityDefinitionRequest(int defineID, String fieldName) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.fieldName = fieldName;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, fieldName, 256);
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the client defined request ID. This will be returned along with the data.
     *
     * @return Client defined request ID.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                ", fieldName='" + fieldName + '\'' +
                ", size=" + size +
                ", version=" + version +
                ", typeID=" + typeID +
                ", identifier=" + identifier +
                '}';
    }
}