package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AICreateSimulatedObject function is used to create AI controlled objects other than aircraft.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateSimulatedObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateSimulatedObject.htm</a>
 */
public class AICreateSimulatedObjectRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x0000002a;

    private final String containerTitle;

    private final InitPosition initPosition;

    private final int requestID;

    AICreateSimulatedObjectRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        initPosition = new InitPosition(buffer);
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AICreateSimulatedObject function is used to create AI controlled objects other than aircraft.
     *
     * @param containerTitle String containing the container title. The container title is case-sensitive and can be
     *                       found in the sim.cfg file.
     * @param initPosition   Specifies the initial position, using a SIMCONNECT_DATA_INITPOSITION structure.
     * @param requestID      Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateSimulatedObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateSimulatedObject.htm</a>
     */
    public AICreateSimulatedObjectRequest(String containerTitle, InitPosition initPosition, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.initPosition = initPosition;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        initPosition.write(outBuffer);
        outBuffer.putInt(requestID);
    }

    /**
     * Returns a string containing the container title. The container title is case-sensitive and can be found in the
     * sim.cfg file.
     *
     * @return The container title.
     */
    public String getContainerTitle() {
        return containerTitle;
    }

    /**
     * Returns the initial position, using a SIMCONNECT_DATA_INITPOSITION structure.
     *
     * @return The initial position.
     */
    public InitPosition getInitPosition() {
        return initPosition;
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
                "containerTitle='" + containerTitle + '\'' +
                ", initPosition=" + initPosition +
                ", requestID=" + requestID +
                '}';
    }
}