package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_AIRemoveObject function is used to remove any object created by the client using one of the AI
 * creation functions.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIRemoveObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIRemoveObject.htm</a>
 */
public class AIRemoveObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x0000002c;

    private final int objectID;

    private final int requestID;

    AIRemoveObjectRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AIRemoveObject function is used to remove any object created by the client using one of the AI
     * creation functions.
     *
     * @param objectID  Specifies the server defined object ID (refer to the SIMCONNECT_RECV_ASSIGNED_OBJECT_ID
     *                  structure).
     * @param requestID Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIRemoveObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIRemoveObject.htm</a>
     */
    public AIRemoveObjectRequest(int objectID, int requestID) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        outBuffer.putInt(requestID);
    }

    /**
     * Returns the server defined object ID.
     *
     * @return Server defined object ID.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Returns the client defined request ID.
     *
     * @return Client defined request ID.
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "objectID=" + objectID +
                ", requestID=" + requestID +
                '}';
    }
}