package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AICreateParkedATCAircraft function is used to create an AI controlled aircraft that is currently
 * parked and does not have a flight plan.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateParkedATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateParkedATCAircraft.htm</a>
 */
public class AICreateParkedATCAircraftRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x00000027;

    private final String containerTitle;

    private final String tailNumber;

    private final String airportIcaoID;

    private final int requestID;

    AICreateParkedATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        airportIcaoID = SimUtil.readString(buffer, 5);
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AICreateParkedATCAircraft function is used to create an AI controlled aircraft that is currently
     * parked and does not have a flight plan.
     *
     * @param containerTitle String containing the container title. The container title is found in the aircraft.cfg
     *                       file. Alternatively, the aircraft title can be obtained via the Aircraft Selector
     *                       (DevMode->Windows->Aircraft selector). Examples of aircraft titles:
     *                       <ul>
     *                       <li><code>Boeing 747-8f Asobo</code></li>
     *                       <li><code>DA62 Asobo</code></li>
     *                       <li><code>title=VL3 Asobo</code></li>
     *                       </ul>
     * @param tailNumber     String containing the tail number. This should have a maximum of 12 characters.
     * @param airportIcaoID  String containing the airport ID. This is the ICAO code string, for example, KSEA for
     *                       SeaTac International.
     * @param requestID      Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateParkedATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateParkedATCAircraft.htm</a>
     */
    public AICreateParkedATCAircraftRequest(String containerTitle, String tailNumber, String airportIcaoID,
            int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.airportIcaoID = airportIcaoID;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        SimUtil.writeString(outBuffer, airportIcaoID, 5);
        outBuffer.putInt(requestID);
    }

    /**
     * Returns a string containing the container title. The container title is found in the aircraft.cfg file.
     * Alternatively, the aircraft title can be obtained via the Aircraft Selector
     * (DevMode->Windows->Aircraft selector).
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
     * Returns the string containing the airport ID. This is the ICAO code string, for example, KSEA for SeaTac International.
     *
     * @return ICAO code string.
     */
    public String getAirportIcaoID() {
        return airportIcaoID;
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
                ", tailNumber='" + tailNumber + '\'' +
                ", airportIcaoID='" + airportIcaoID + '\'' +
                ", requestID=" + requestID +
                '}';
    }
}