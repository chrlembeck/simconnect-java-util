package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AISetAircraftFlightPlan function is used to set or change the flight plan of an AI controlled aircraft.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AISetAircraftFlightPlan.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AISetAircraftFlightPlan.htm</a>
 */
public class AISetAircraftFlightPlanRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x0000002d;

    private final int objectID;

    private final String flightPlanPath;

    private final int requestID;

    AISetAircraftFlightPlanRequest(ByteBuffer buffer) {
        super(buffer);
        objectID = buffer.getInt();
        flightPlanPath = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AISetAircraftFlightPlan function is used to set or change the flight plan of an AI controlled
     * aircraft.
     *
     * @param objectID       Specifies the server defined object ID.
     * @param flightPlanPath String containing the path to the flight plan file. Flight plans have the extension .pln,
     *                       but no need to enter an extension here. The easiest way to create flight plans is to create
     *                       them from within the simulation, and then save them off for use with the AI controlled
     *                       aircraft.
     * @param requestID      Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AISetAircraftFlightPlan.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AISetAircraftFlightPlan.htm</a>
     */
    public AISetAircraftFlightPlanRequest(int objectID, String flightPlanPath, int requestID) {
        super(TYPE_ID);
        this.objectID = objectID;
        this.flightPlanPath = flightPlanPath;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(objectID);
        SimUtil.writeString(outBuffer, flightPlanPath, SimUtil.MAX_PATH);
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
     * Returns the string containing the path to the flight plan file. Flight plans have the extension .pln, but no need
     * to enter an extension here. The easiest way to create flight plans is to create them from within the simulation,
     * and then save them off for use with the AI controlled aircraft.
     *
     * @return Path to the flight plan file.
     */
    public String getFlightPlanPath() {
        return flightPlanPath;
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
                ", flightPlanPath='" + flightPlanPath + '\'' +
                ", requestID=" + requestID +
                '}';
    }
}