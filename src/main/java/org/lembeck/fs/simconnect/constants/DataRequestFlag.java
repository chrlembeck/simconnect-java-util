package org.lembeck.fs.simconnect.constants;

/**
 * Flags for the RequestDataOnSimObject and RecvSimobjectDataResponse request.
 */
public interface DataRequestFlag {

    /**
     * The default, data will be sent strictly according to the defined period.
     */
    int DATA_REQUEST_FLAG_DEFAULT = 0;

    /**
     * Data will only be sent to the client when one or more values have changed. If this is the only flag set, then all
     * the variables in a data definition will be returned if just one of the values changes.
     */
    int DATA_REQUEST_FLAG_CHANGED = 1;

    /**
     * Requested data will be sent in tagged format (datum ID/value pairs). Tagged format requires that a datum
     * reference ID is returned along with the data value, in order that the client code is able to identify the
     * variable. This flag is usually set in conjunction with the previous flag, but it can be used on its own to return
     * all the values in a data definition in datum ID/value pairs. See the SIMCONNECT_RECV_SIMOBJECT_DATA structure for
     * more details.
     */
    int SIMCONNECT_DATA_REQUEST_FLAG_TAGGED = 2;
}