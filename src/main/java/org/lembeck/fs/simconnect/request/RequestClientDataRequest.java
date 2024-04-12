package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.DataRequestFlag;
import org.lembeck.fs.simconnect.constants.SimconnectPeriod;
import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestClientData function is used to request that the specified data in an area created by
 * another client be sent to this client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestClientData.htm</a>
 */
public class RequestClientDataRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000003b;

    private final int clientDataID;

    private final int requestID;

    private final int defineID;

    private final SimconnectPeriod period;

    private final int flags;

    private final int origin;

    private final int interval;

    private final int limit;

    RequestClientDataRequest(ByteBuffer buffer) {
        super(buffer);
        clientDataID = buffer.getInt();
        requestID = buffer.getInt();
        defineID = buffer.getInt();
        period = SimconnectPeriod.ofId(buffer.getInt());
        flags = buffer.getInt();
        origin = buffer.getInt();
        interval = buffer.getInt();
        limit = buffer.getInt();
    }

    /**
     * The SimConnect_RequestClientData function is used to request that the specified data in an area created by
     * another client be sent to this client.
     *
     * @param clientDataID Specifies the ID of the client data area. Before calling this function for the first time on
     *                     one client area, call SimConnect_MapClientDataNameToID to map an ID to the unique client data
     *                     area name. This name must match the name specified by the client creating the data area with
     *                     the SimConnect_MapClientDataNameToID and SimConnect_CreateClientData functions.
     * @param requestID    Specifies the ID of the client-defined request. This is used later by the client to identify
     *                     which data has been received. This value should be unique for each request, re-using a
     *                     RequestID will overwrite any previous request using the same ID.
     * @param defineID     Specifies the ID of the client-defined data definition. This definition specifies the data
     *                     that should be sent to the client.
     * @param period       One member of the SIMCONNECT_CLIENT_DATA_PERIOD enumeration type, specifying how often the
     *                     data is to be sent by the server and received by the client.
     * @param flags        A DWORD containing one or more of the values from the {@link DataRequestFlag} interface.
     * @param origin       The number of Period events that should elapse before transmission of the data begins. The
     *                     default is zero, which means transmissions will start immediately.
     * @param interval     The number of Period events that should elapse between transmissions of the data. The default
     *                     is zero, which means the data is transmitted every Period.
     * @param limit        The number of times the data should be transmitted before this communication is ended. The
     *                     default is zero, which means the data should be transmitted endlessly.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestClientData.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestClientData.htm</a>
     */
    public RequestClientDataRequest(int clientDataID, int requestID, int defineID, SimconnectPeriod period, int flags,
            int origin, int interval, int limit) {
        super(TYPE_ID);
        this.clientDataID = clientDataID;
        this.requestID = requestID;
        this.defineID = defineID;
        this.period = period;
        this.flags = flags;
        this.origin = origin;
        this.interval = interval;
        this.limit = limit;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(clientDataID);
        outBuffer.putInt(requestID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(period.ordinal());
        outBuffer.putInt(flags);
        outBuffer.putInt(origin);
        outBuffer.putInt(interval);
        outBuffer.putInt(limit);
    }

    /**
     * Returns the ID of the client data area.
     *
     * @return ID of the client data area.
     */
    public int getClientDataID() {
        return clientDataID;
    }

    /**
     * Returns the ID of the client-defined request.
     *
     * @return ID of the client-defined request.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns the ID of the client-defined data definition. This definition specifies the data that should be sent to the client.
     *
     * @return ID of the client-defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns how often the data should be sent by the server.
     *
     * @return Period how often the data should be sent by the server.
     */
    public SimconnectPeriod getPeriod() {
        return period;
    }

    /**
     * Returns one or more of the values from the {@link DataRequestFlag} interface.
     *
     * @return One or more of the values from the {@link DataRequestFlag} interface.
     */
    public int getFlags() {
        return flags;
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
        return getClass().getSimpleName() + "{" +
                "clientDataID=" + clientDataID +
                ", requestID=" + requestID +
                ", defineID=" + defineID +
                ", period=" + period +
                ", flags=" + flags +
                ", origin=" + origin +
                ", interval=" + interval +
                ", limit=" + limit +
                '}';
    }
}