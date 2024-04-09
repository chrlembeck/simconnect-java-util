package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

public class AddToClientDataDefinitionRequest extends SimRequest {

    /**
     * Automatically compute offset of the ClientData variable.
     */
    public static final int CLIENTDATAOFFSET_AUTO = -1;

    /**
     * 8-bit integer number.
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT8 = -1;   //  8-bit integer number

    /**
     * 16-bit integer number
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT16 = -2;   // 16-bit integer number

    /**
     * 32-bit integer number
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT32 = -3;   // 32-bit integer number

    /**
     * 64-bit integer number
     */
    public static int SIMCONNECT_CLIENTDATATYPE_INT64 = -4;   // 64-bit integer number

    /**
     * 32-bit floating-point number (float)
     */
    public static int SIMCONNECT_CLIENTDATATYPE_FLOAT32 = -5;   // 32-bit floating-point number (float)

    /**
     * 64-bit floating-point number (double)
     */
    public static int SIMCONNECT_CLIENTDATATYPE_FLOAT64 = -6;   // 64-bit floating-point number (double)

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000039;

    private final int defineID;
    private final int offset;
    private final int sizeOrType;
    private final float epsilon;
    private final int datumID;

    AddToClientDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        offset = buffer.getInt();
        sizeOrType = buffer.getInt();
        epsilon = buffer.getFloat();
        datumID = buffer.getInt();
    }

    public AddToClientDataDefinitionRequest(int defineID, int offset, int sizeOrType, float epsilon, int datumID) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.offset = offset;
        this.sizeOrType = sizeOrType;
        this.epsilon = epsilon;
        this.datumID = datumID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        outBuffer.putInt(offset);
        outBuffer.putInt(sizeOrType);
        outBuffer.putFloat(epsilon);
        outBuffer.putInt(datumID);
    }

    public int getDefineID() {
        return defineID;
    }

    public int getOffset() {
        return offset;
    }

    public int getSizeOrType() {
        return sizeOrType;
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
        return getClass().getSimpleName() + "{" +
                "defineID=" + defineID +
                ", offset=" + offset +
                ", sizeOrType=" + sizeOrType +
                ", epsilon=" + epsilon +
                ", datumID=" + datumID +
                '}';
    }
}