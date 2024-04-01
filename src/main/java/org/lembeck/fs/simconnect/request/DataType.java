package org.lembeck.fs.simconnect.request;

public enum DataType {

    INVALID(-1),
    INT32(4),
    INT64(8),
    FLOAT32(4),
    FLOAT64(8),
    STRING8(8),
    STRING32(32),
    STRING64(64),
    STRING128(128),
    STRING256(256),
    STRING260(260),
    STRINGV(-1),
    INITPOSITION(56),
    MARKERSTATE(68),
    WAYPOINT(48),
    LATLONALT(24),
    XYZ(24);

    private final int size;

    DataType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
