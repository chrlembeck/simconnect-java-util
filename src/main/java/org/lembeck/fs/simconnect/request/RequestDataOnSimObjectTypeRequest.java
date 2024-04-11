package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.SimObjectType;

import java.nio.ByteBuffer;

/**
 * The SimConnect_RequestDataOnSimObjectType function is used to retrieve information about simulation objects of a
 * given type that are within a specified radius of the user's aircraft.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm</a>
 */
public class RequestDataOnSimObjectTypeRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000f;

    private final int requestID;

    private final int defineID;

    private final int radiusMeters;

    private final SimObjectType type;

    RequestDataOnSimObjectTypeRequest(ByteBuffer buffer) {
        super(buffer);
        requestID = buffer.getInt();
        defineID = buffer.getInt();
        radiusMeters = buffer.getInt();
        type = SimObjectType.ofId(buffer.getInt());
    }

    /**
     * The SimConnect_RequestDataOnSimObjectType function is used to retrieve information about simulation objects of a
     * given type that are within a specified radius of the user's aircraft.
     *
     * @param requestID    Specifies the ID of the client defined request. This is used later by the client to identify
     *                     which data has been received. This value should be unique for each request, re-using a
     *                     RequestID will overwrite any previous request using the same ID.
     * @param defineID     Specifies the ID of the client defined data definition.
     * @param radiusMeters Double word containing the radius in meters. If this is set to zero only information on the
     *                     user aircraft will be returned, although this value is ignored if type is set to
     *                     SIMCONNECT_SIMOBJECT_TYPE_USER. The error SIMCONNECT_EXCEPTION_OUT_OF_BOUNDS will be returned
     *                     if a radius is given and it exceeds the maximum allowed (200,000 meters, or 200 Km).
     * @param type         Specifies the type of object to receive information on. One member of the
     *                     SIMCONNECT_SIMOBJECT_TYPE enumeration type
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm</a>
     */
    public RequestDataOnSimObjectTypeRequest(int requestID, int defineID, int radiusMeters, SimObjectType type) {
        super(TYPE_ID);
        this.requestID = requestID;
        this.defineID = defineID;
        this.radiusMeters = radiusMeters;
        this.type = type;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(requestID);
        outBuffer.putInt(defineID);
        outBuffer.putInt(radiusMeters);
        outBuffer.putInt(type.ordinal());
    }

    /**
     * Returns the ID of the client defined request. This is used later by the client to identify which data has been
     * received. This value should be unique for each request, re-using a RequestID will overwrite any previous request
     * using the same ID.
     *
     * @return ID of the client defined request.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Specifies the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Double word containing the radius in meters. If this is set to zero only information on the user aircraft will be
     * returned, although this value is ignored if type is set to SIMCONNECT_SIMOBJECT_TYPE_USER. The error
     * SIMCONNECT_EXCEPTION_OUT_OF_BOUNDS will be returned if a radius is given and it exceeds the maximum allowed
     * (200,000 meters, or 200 Km).
     *
     * @return Radius in meters.
     */
    public int getRadiusMeters() {
        return radiusMeters;
    }

    /**
     * Returns the type of object to receive information on. One member of the SIMCONNECT_SIMOBJECT_TYPE enumeration type
     *
     * @return Type of object to receive information on.
     */
    public SimObjectType getType() {
        return type;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", requestID='" + requestID + "'" +
                ", defineID=" + defineID +
                ", radiusMeters=" + radiusMeters +
                ", type=" + type +
                "}";
    }
}