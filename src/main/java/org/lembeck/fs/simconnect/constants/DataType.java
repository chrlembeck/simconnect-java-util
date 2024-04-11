package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_DATATYPE enumeration type is used with the SimConnect_AddToDataDefinition call to specify the data
 * type that the server should use to return the specified data to the client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATATYPE.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATATYPE.htm</a>
 */
public enum DataType {

    /**
     * Specifies an invalid data type marker (should not be used).
     */
    INVALID(-1),

    /**
     * Specifies a 32 bit unsigned integer value.
     */
    INT32(4),

    /**
     * Specifies a 64 bit signed integer value.
     */
    INT64(8),

    /**
     * Specifies a 32 bit floating point number.
     */
    FLOAT32(4),

    /**
     * Specifies a 64 bit floating point number.
     */
    FLOAT64(8),

    /**
     * Specifies strings of the length up to 8 characters.
     */
    STRING8(8),

    /**
     * Specifies strings of the length up to 32 characters.
     */
    STRING32(32),

    /**
     * Specifies strings of the length up to 64 characters.
     */
    STRING64(64),

    /**
     * Specifies strings of the length up to 128 characters.
     */
    STRING128(128),

    /**
     * Specifies strings of the length up to 256 characters.
     */
    STRING256(256),

    /**
     * Specifies strings of the length up to 260 characters.
     */
    STRING260(260),

    /**
     * Specifies a variable length string.
     */
    STRINGV(-1),

    /**
     * Specifies the SIMCONNECT_DATA_INITPOSITION structure.
     *
     * @see org.lembeck.fs.simconnect.request.InitPosition
     */
    INITPOSITION(56),

    /**
     * Specifies the SIMCONNECT_DATA_MARKERSTATE structure.
     */
    MARKERSTATE(68),

    /**
     * Specifies the SIMCONNECT_DATA_WAYPOINT structure.
     */
    WAYPOINT(48),

    /**
     * Specifies the SIMCONNECT_DATA_LATLONALT structure.
     *
     * @see org.lembeck.fs.simconnect.response.LatLonAlt
     */
    LATLONALT(24),

    /**
     * Specifies the SIMCONNECT_DATA_XYZ structure.
     *
     * @see org.lembeck.fs.simconnect.response.DataXYZ
     */
    XYZ(24),

    /**
     * Specifies an unknown data type (should not be used).
     */
    UNKNOWN(-1);

    private final int size;

    DataType(int size) {
        this.size = size;
    }

    /**
     * Returns the data type that is specified by the given identifier.
     *
     * @param id Identifier for the data type.
     * @return The data type that is specified by the given identifier.
     */
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

    /**
     * Returns the size of one member of the data type in bytes.
     *
     * @return Size of the data type in bytes.
     */
    public int getSize() {
        return size;
    }
}