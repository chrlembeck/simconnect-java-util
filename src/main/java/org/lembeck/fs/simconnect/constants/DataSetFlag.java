package org.lembeck.fs.simconnect.constants;

/**
 * Flags for the format of client sent data.
 *
 * @see org.lembeck.fs.simconnect.SimConnect#setDataOnSimObject(int, int, DataSetFlag, int, int, byte[])
 * @see org.lembeck.fs.simconnect.SimConnect#setClientDataDefinition(int, int, boolean, int, int, byte[])
 */
public enum DataSetFlag {

    /**
     * Data is in default format.
     */
    NOT_TAGGED,

    /**
     * Data is in tagged format.
     */
    TAGGED
}