package org.lembeck.fs.simconnect.request;

import java.nio.ByteBuffer;

/**
 * The SimConnect_AIReleaseControl function is used to clear the AI control of a simulated object, typically an
 * aircraft, in order for it to be controlled by a SimConnect client.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIReleaseControl.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIReleaseControl.htm</a>
 */
public class AIReleaseControlRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x0000002b;

    private final int objectID;

    private final int requestID;

    AIReleaseControlRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AIReleaseControl function is used to clear the AI control of a simulated object, typically an
     * aircraft, in order for it to be controlled by a SimConnect client.
     *
     * @param objectID  Specifies the server defined object ID.
     * @param requestID Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIReleaseControl.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AIReleaseControl.htm</a>
     */
    public AIReleaseControlRequest(int objectID, int requestID) {
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
     * @return The server defined object ID.
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Returns the client defined request ID.
     *
     * @return The client defined request ID.
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