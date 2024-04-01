package org.lembeck.fs.simconnect.response;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RecvFacilityDataResponse extends SimResponse {

    private final int requestID;
    private final int uniqueRequestID;
    private final int parentUniqueRequestID;
    private final FacilityDataType type;
    private final boolean listItem;
    private final int itemIndex;
    private final int listSize;
    private final byte[] data;

    RecvFacilityDataResponse(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        uniqueRequestID = buffer.getInt();
        parentUniqueRequestID = buffer.getInt();
        type = FacilityDataType.values()[buffer.getInt()];
        listItem = buffer.getInt() != 0;
        itemIndex = buffer.getInt();
        listSize = buffer.getInt();
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }

    public int getRequestID() {
        return requestID;
    }

    public int getUniqueRequestID() {
        return uniqueRequestID;
    }

    public int getParentUniqueRequestID() {
        return parentUniqueRequestID;
    }

    public FacilityDataType getType() {
        return type;
    }

    public boolean isListItem() {
        return listItem;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getListSize() {
        return listSize;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "requestID=" + requestID +
                ", uniqueRequestID=" + uniqueRequestID +
                ", parentUniqueRequestID=" + parentUniqueRequestID +
                ", type=" + type +
                ", listItem=" + listItem +
                ", itemIndex=" + itemIndex +
                ", listSize=" + listSize +
                ", data=" + Arrays.toString(data) +
                ", size=" + size +
                ", version=" + version +
                ", typeId=" + typeId +
                '}';
    }
}