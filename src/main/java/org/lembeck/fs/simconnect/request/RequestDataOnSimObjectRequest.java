package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.DataRequestFlag;
import org.lembeck.fs.simconnect.constants.SimconnectPeriod;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestDataOnSimObject function is used to request when the SimConnect client is to receive data
 * values for a specific object.
 *
 * @see DataRequestFlag
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm</a>
 */
public class RequestDataOnSimObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000e;

    private final int dataRequestID;

    private final int dataDefinitionID;

    private final int objectID;

    private final SimconnectPeriod period;

    private final int dataRequestFlags;

    private final int origin;

    private final int interval;

    private final int limit;


    RequestDataOnSimObjectRequest(ByteBuffer buffer) {
        super(buffer);
        dataRequestID = buffer.getInt();
        dataDefinitionID = buffer.getInt();
        objectID = buffer.getInt();
        period = SimconnectPeriod.ofId(buffer.getInt());
        dataRequestFlags = buffer.getInt();
        origin = buffer.getInt();
        interval = buffer.getInt();
        limit = buffer.getInt();
    }

    /**
     * The SimConnect_RequestDataOnSimObject function is used to request when the SimConnect client is to receive data
     * values for a specific object.
     *
     * @param dataRequestID    Specifies the ID of the client defined group.
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @param objectID         Specifies the ID of the Microsoft Flight Simulator object that the data should be about.
     *                         This ID can be SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained
     *                         from a SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to
     *                         SimConnect_RequestDataOnSimObjectType. Also refer to the note on developing clients for
     *                         multiplayer mode in the Remarks section below.
     * @param period           One member of the SIMCONNECT_PERIOD enumeration type, specifying how often the data is to
     *                         be sent by the server and received by the client.
     * @param dataRequestFlags A DWORD containing one or more of the values shown in the table below this one.
     * @param origin           The number of Period events that should elapse before transmission of the data begins.
     *                         The default is zero, which means transmissions will start immediately.
     * @param interval         The number of Period events that should elapse between transmissions of the data. The
     *                         default is zero, which means the data is transmitted every Period.
     * @param limit            The number of times the data should be transmitted before this communication is ended.
     *                         The default is zero, which means the data should be transmitted endlessly.
     * @see DataRequestFlag
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm</a>
     */
    public RequestDataOnSimObjectRequest(int dataRequestID, int dataDefinitionID, int objectID, SimconnectPeriod period, int dataRequestFlags, int origin, int interval, int limit) {
        super(TYPE_ID);
        this.dataRequestID = dataRequestID;
        this.dataDefinitionID = dataDefinitionID;
        this.objectID = objectID;
        this.period = period;
        this.dataRequestFlags = dataRequestFlags;
        this.origin = origin;
        this.interval = interval;
        this.limit = limit;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(dataRequestID);
        outBuffer.putInt(dataDefinitionID);
        outBuffer.putInt(objectID);
        outBuffer.putInt(period.ordinal());
        outBuffer.putInt(dataRequestFlags);
        outBuffer.putInt(origin);
        outBuffer.putInt(interval);
        outBuffer.putInt(limit);
    }

    /**
     * Returns the ID of the client defined group.
     *
     * @return ID of the client defined group.
     */
    public int getDataRequestID() {
        return dataRequestID;
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDataDefinitionID() {
        return dataDefinitionID;
    }

    /**
     * Specifies the ID of the Microsoft Flight Simulator object that the data should be about. This ID can be
     * SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained from a
     * SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to SimConnect_RequestDataOnSimObjectType.
     * Also refer to the note on developing clients for multiplayer mode in the Remarks section below.
     *
     * @return ID of the Microsoft Flight Simulator object.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * One member of the SIMCONNECT_PERIOD enumeration type, specifying how often the data is to be sent by the server
     * and received by the client.
     *
     * @return Period of actualizations.
     */
    public SimconnectPeriod getPeriod() {
        return period;
    }

    /**
     * Flags as defined in {@link DataRequestFlag}.
     *
     * @return Flags.
     */
    public int getDataRequestFlags() {
        return dataRequestFlags;
    }

    /**
     * Returns the number of Period events that should elapse before transmission of the data begins. The default is
     * zero, which means transmissions will start immediately.
     *
     * @return Number of Period events that should elapse before transmission of the data begins.
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * Returns the number of Period events that should elapse between transmissions of the data. The default is zero,
     * which means the data is transmitted every Period.
     *
     * @return Number of Period events that should elapse between transmissions of the data.
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Returns the number of times the data should be transmitted before this communication is ended. The default is
     * zero, which means the data should be transmitted endlessly.
     *
     * @return Number of times the data should be transmitted before this communication is ended.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID: " + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", dataRequestID=" + dataRequestID +
                ", dataDefinitionID=" + dataDefinitionID +
                ", objectID=" + objectID +
                ", period=" + period +
                ", dataRequestFlags=" + dataRequestFlags +
                ", interval=" + interval +
                ", limit=" + limit +
                "}";
    }
}