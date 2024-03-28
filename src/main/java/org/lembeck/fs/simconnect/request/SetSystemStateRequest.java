package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class SetSystemStateRequest extends SimRequest {

    public static final int TYPE_ID = 0xf0000036;

    private final String state;
    private final int intParam;
    private final float floatParam;
    private final String stringParam;

    SetSystemStateRequest(ByteBuffer buffer) {
        super(buffer);
        this.state = SimUtil.readString(buffer, 256);
        this.intParam = buffer.getInt();
        this.floatParam = buffer.getFloat();
        this.stringParam = SimUtil.readString(buffer, 256);
    }

    public SetSystemStateRequest(String state, int intParam, float floatParam, String stringParam) {
        super(TYPE_ID);
        this.state = state;
        this.intParam = intParam;
        this.floatParam = floatParam;
        this.stringParam = stringParam;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, state, 256);
        outBuffer.putInt(intParam);
        outBuffer.putFloat(floatParam);
        SimUtil.writeString(outBuffer, stringParam, 256);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", requestID='" + state + "'" +
                ", intParam=" + intParam +
                ", floatParam=" + floatParam +
                ", stringParam='" + stringParam + "'" +
                "}";
    }
}