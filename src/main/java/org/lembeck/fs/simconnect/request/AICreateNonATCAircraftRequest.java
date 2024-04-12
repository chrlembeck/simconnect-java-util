package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SimConnect_AICreateNonATCAircraft function is used to create an aircraft that is not flying under ATC control
 * (so is typically flying under VFR rules).
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateNonATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateNonATCAircraft.htm</a>
 */
public class AICreateNonATCAircraftRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0x00000029;

    private final String containerTitle;

    private final String tailNumber;

    private final InitPosition initPosition;

    private final int requestID;

    AICreateNonATCAircraftRequest(ByteBuffer buffer) {
        super(buffer);
        containerTitle = SimUtil.readString(buffer, 256);
        tailNumber = SimUtil.readString(buffer, 12);
        initPosition = new InitPosition(buffer);
        requestID = buffer.getInt();
    }

    /**
     * The SimConnect_AICreateNonATCAircraft function is used to create an aircraft that is not flying under ATC control
     * (so is typically flying under VFR rules).
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
     * @param initPosition   Specifies the initial position, using a SIMCONNECT_DATA_INITPOSITION structure.
     * @param requestID      Specifies the client defined request ID.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateNonATCAircraft.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/AI_Object/SimConnect_AICreateNonATCAircraft.htm</a>
     */
    public AICreateNonATCAircraftRequest(String containerTitle, String tailNumber, InitPosition initPosition,
            int requestID) {
        super(TYPE_ID);
        this.containerTitle = containerTitle;
        this.tailNumber = tailNumber;
        this.initPosition = initPosition;
        this.requestID = requestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        SimUtil.writeString(outBuffer, containerTitle, 256);
        SimUtil.writeString(outBuffer, tailNumber, 12);
        initPosition.write(outBuffer);
        outBuffer.putInt(requestID);
    }

    /**
     * Returns a string containing the container title. The container title is found in the aircraft.cfg file.
     * Alternatively, the aircraft title can be obtained via the Aircraft Selector (DevMode->Windows->Aircraft
     * selector).
     *
     * @return The container title.
     */
    public String getContainerTitle() {
        return containerTitle;
    }

    /**
     * Returns a string containing the tail number. This should have a maximum of 12 characters.
     *
     * @return The tail number.
     */
    public String getTailNumber() {
        return tailNumber;
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
                ", tailNumber='" + tailNumber + '\'' +
                ", initPosition=" + initPosition +
                ", requestID=" + requestID +
                '}';
    }
}