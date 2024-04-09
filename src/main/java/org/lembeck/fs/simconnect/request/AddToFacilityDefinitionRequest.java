package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

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

    public int getDefineID() {
        return defineID;
    }

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