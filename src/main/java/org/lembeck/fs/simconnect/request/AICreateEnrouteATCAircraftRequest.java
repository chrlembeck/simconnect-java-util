package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AICreateEnrouteATCAircraft function is used to create an AI controlled aircraft that is about to
 * start or is already underway on its flight plan.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateEnrouteATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateEnrouteATCAircraft.htm</a>
 */
public class AICreateEnrouteATCAircraftRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x00000028;

    private final String containerTitle;

    private final String tailNumber;

    private final int flightNumber;

    private final String flightPlanPath;

    private final float flightPlanPosition;

    private final boolean touchAndGo;

    private final int requestID;

    AICreateEnrouteATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        flightNumber = buffer.getInt();
        flightPlanPath = SimUtil.readString(buffer, SimUtil.MAX_PATH);
        flightPlanPosition = buffer.getFloat();
        touchAndGo = buffer.getInt() != 0;
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AICreateEnrouteATCAircraft function is used to create an AI controlled aircraft that is about to
     * start or is already underway on its flight plan.
     *
     * @param containerTitle     String containing the container title. The container title is found in the aircraft.cfg
     *                           file. Alternatively, the aircraft title can be obtained via the Aircraft Selector
     *                           (DevMode->Windows->Aircraft selector). Examples of aircraft titles:
     *                           <ul>
     *                           <li><code>Boeing 747-8f Asobo</code></li>
     *                           <li><code>DA62 Asobo</code></li>
     *                           <li><code>title=VL3 Asobo</code></li>
     *                           </ul>
     * @param tailNumber         String containing the tail number. This should have a maximum of 12 characters.
     * @param flightNumber       Integer containing the flight number. There is no specific maximum length of this
     *                           number. Any negative number indicates that there is no flight number.
     * @param flightPlanPath     String containing the path to the flight plan file. Flight plans have the extension
     *                           .pln, but no need to enter an extension here. The easiest way to create flight plans
     *                           is to create them from within Microsoft Flight Simulator itself, and then save them
     *                           off for use with the AI controlled aircraft.
     * @param flightPlanPosition Double floating point number containing the flight plan position. The number before the
     *                           point contains the waypoint index, and the number afterwards how far along the route to
     *                           the next waypoint the aircraft is to be positioned. The first waypoint index is 0. For
     *                           example, 0.0 indicates that the aircraft has not started on the flight plan, 2.5 would
     *                           indicate the aircraft is to be initialized halfway between the third and fourth
     *                           waypoints (which would have indexes 2 and 3). The waypoints are those recorded in the
     *                           flight plan, which may just be two airports, and do not include any taxiway points on
     *                           the ground. Also there is a threshold that will ignore requests to have an aircraft
     *                           taxiing or taking off, or landing. So set the value after the point to ensure the
     *                           aircraft will be in level flight.
     * @param touchAndGo         Set to True to indicate that landings should be touch and go, and not full stop landings.
     * @param requestID          Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateEnrouteATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateEnrouteATCAircraft.htm</a>
     */
    public AICreateEnrouteATCAircraftRequest(String containerTitle, String tailNumber, int flightNumber,
            String flightPlanPath, float flightPlanPosition, boolean touchAndGo, int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.flightNumber = flightNumber;
        this.flightPlanPath = flightPlanPath;
        this.flightPlanPosition = flightPlanPosition;
        this.touchAndGo = touchAndGo;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        outBuffer.putInt(flightNumber);
        SimUtil.writeString(outBuffer, flightPlanPath, SimUtil.MAX_PATH);
        outBuffer.putFloat(flightPlanPosition);
        outBuffer.putInt(touchAndGo ? 1 : 0);
        outBuffer.putInt(requestID);
    }

    /**
     * Returns the string containing the container title. The container title is found in the aircraft.cfg file.
     * Alternatively, the aircraft title can be obtained via the Aircraft Selector (DevMode->Windows->Aircraft
     * selector).
     *
     * @return String containing the container title.
     */
    public String getContainerTitle() {
        return containerTitle;
    }

    /**
     * Returns a string containing the tail number. This should have a maximum of 12 characters.
     *
     * @return String containing the tail number.
     */
    public String getTailNumber() {
        return tailNumber;
    }

    /**
     * Returns an integer containing the flight number. There is no specific maximum length of this number. Any negative
     * number indicates that there is no flight number.
     *
     * @return The flight number.
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns a string containing the path to the flight plan file. Flight plans have the extension .pln, but no need
     * to enter an extension here. The easiest way to create flight plans is to create them from within Microsoft Flight
     * Simulator itself, and then save them off for use with the AI controlled aircraft.
     *
     * @return The path to the flight plan file.
     */
    public String getFlightPlanPath() {
        return flightPlanPath;
    }

    /**
     * Returns a double floating point number containing the flight plan position. The number before the point contains
     * the waypoint index, and the number afterwards how far along the route to the next waypoint the aircraft is to be
     * positioned. The first waypoint index is 0. For example, 0.0 indicates that the aircraft has not started on the
     * flight plan, 2.5 would indicate the aircraft is to be initialized halfway between the third and fourth waypoints
     * (which would have indexes 2 and 3). The waypoints are those recorded in the flight plan, which may just be two
     * airports, and do not include any taxiway points on the ground. Also there is a threshold that will ignore
     * requests to have an aircraft taxiing or taking off, or landing. So set the value after the point to ensure the
     * aircraft will be in level flight.
     *
     * @return The flight plan position.
     */
    public float getFlightPlanPosition() {
        return flightPlanPosition;
    }

    /**
     * Returns whether the landings are touch and go or full stop landings.
     *
     * @return True to indicate that landings should be touch and go, false to indicate full stop landings.
     */
    public boolean isTouchAndGo() {
        return touchAndGo;
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
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", containerTitle='" + containerTitle + "'" +
                ", tailNumber='" + tailNumber + "'" +
                ", flightNumber=" + flightNumber +
                ", flightPlanPath='" + flightPlanPath + "'" +
                ", flightPlanPosition=" + flightPlanPosition +
                ", touchAndGo=" + touchAndGo +
                ", requestID=" + requestID +
                "}";
    }
}