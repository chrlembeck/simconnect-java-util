package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.DataType;
import java.nio.ByteBuffer;

public class AddToDataDefinitionRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000c;

    public static final int UNUSED = 0xffffffff;

    private final int defineID;
    private final String datumName;
    private final String unitsName;
    private final DataType datumType;
    private final float epsilon;
    private final int datumID;

    AddToDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        datumName = SimUtil.readString(buffer, 256);
        unitsName = SimUtil.readString(buffer, 256);
        datumType = DataType.ofId(buffer.getInt());
        epsilon = buffer.getFloat();
        datumID = buffer.getInt();
    }

    public AddToDataDefinitionRequest(int defineID, String datumName, String unitsName, DataType datumType, float epsilon) {
        this(defineID, datumName, unitsName, datumType, epsilon, UNUSED);
    }

    public AddToDataDefinitionRequest(int defineID, String datumName, String unitsName, DataType datumType, float epsilon, int datumID) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.datumName = datumName;
        this.unitsName = unitsName;
        this.datumType = datumType;
        this.epsilon = epsilon;
        this.datumID = datumID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, datumName, 256);
        SimUtil.writeString(outBuffer, unitsName, 256);
        outBuffer.putInt(datumType.ordinal());
        outBuffer.putFloat(epsilon);
        outBuffer.putInt(datumID);
    }

    public int getDefineID() {
        return defineID;
    }

    public String getDatumName() {
        return datumName;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public DataType getDatumType() {
        return datumType;
    }

    public float getEpsilon() {
        return epsilon;
    }

    public int getDatumID() {
        return datumID;
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
                ", defineID='" + defineID + "'" +
                ", datumName='" + datumName + "'"+
                ", unitsName='" + unitsName + "'"+
                ", datumType=" + datumType +
                ", fEpsilon=" + epsilon +
                ", datumId=" + datumID +
                "}";
    }
}
