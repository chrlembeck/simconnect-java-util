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
    XYZ(24),
    UNKNOWN(-1);


    private final int size;

    DataType(int size) {
        this.size = size;
    }

    public static DataType ofId(int id) {
        return switch (id) {
            case 0 -> INVALID;
            case 1 -> INT32;
            case 2 -> INT64;
            case 3 -> FLOAT32;
            case 4 -> FLOAT64;
            case 5 -> STRING8;
            case 6 -> STRING32;
            case 7 -> STRING64;
            case 8 -> STRING128;
            case 9 -> STRING256;
            case 10 -> STRING260;
            case 11 -> STRINGV;
            case 12 -> INITPOSITION;
            case 13 -> MARKERSTATE;
            case 14 -> WAYPOINT;
            case 15 -> LATLONALT;
            case 16 -> XYZ;
            default -> UNKNOWN;
        };
    }

    public int getSize() {
        return size;
    }
}
