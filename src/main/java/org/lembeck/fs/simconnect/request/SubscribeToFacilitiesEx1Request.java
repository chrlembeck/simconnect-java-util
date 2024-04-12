package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_SubscribeToFacilities_EX1 function is used to request notifications when a facility of a certain
 * type is added to the facilities cache, with the ability to specify callbacks.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities_EX1.htm</a>
 */
public class SubscribeToFacilitiesEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf0000047;

    private final FacilityListType type;

    private final int newElemInRangeRequestID;

    private final int oldElemOutRangeRequestID;

    SubscribeToFacilitiesEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        newElemInRangeRequestID = buffer.getInt();
        oldElemOutRangeRequestID = buffer.getInt();
    }

    /**
     * The SimConnect_SubscribeToFacilities_EX1 function is used to request notifications when a facility of a certain
     * type is added to the facilities cache, with the ability to specify callbacks.
     *
     * @param type                     Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param newElemInRangeRequestID  Request id for messages about new elements considered to be in range of the
     *                                 reality bubble. If -1 is used, then the client won't receive messages for
     *                                 elements coming into range.
     *                                 NOTE: This cannot be set to -1 if oldElemOutRangeRequestID is also -1.
     * @param oldElemOutRangeRequestID Request id for messages about an element that is newly considered out of range of
     *                                 the reality bubble. If -1 is used, then the client won't receive messages for
     *                                 elements coming into range.
     *                                 NOTE: This cannot be set to -1 if newElemInRangeRequestID is also -1.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_SubscribeToFacilities_EX1.htm</a>
     */
    public SubscribeToFacilitiesEx1Request(FacilityListType type, int newElemInRangeRequestID,
            int oldElemOutRangeRequestID) {
        super(TYPE_ID);
        this.type = type;
        this.newElemInRangeRequestID = newElemInRangeRequestID;
        this.oldElemOutRangeRequestID = oldElemOutRangeRequestID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.putInt(newElemInRangeRequestID);
        outBuffer.putInt(oldElemOutRangeRequestID);
    }

    /**
     * Returns the type of the facilities.
     *
     * @return Type of the facilities.
     */
    public FacilityListType getType() {
        return type;
    }

    /**
     * Returns the request id for messages about new elements considered to be in range of the reality bubble. If -1 is
     * used, then the client won't receive messages for elements coming into range.
     *
     * @return The request id for messages about new elements considered to be in range of the reality bubble.
     */
    public int getNewElemInRangeRequestID() {
        return newElemInRangeRequestID;
    }

    /**
     * Returns the request id for messages about an element that is newly considered out of range of the reality bubble.
     * If -1 is used, then the client won't receive messages for elements coming into range.
     *
     * @return The request id for messages about an element that is newly considered out of range of the reality bubble.
     */
    public int getOldElemOutRangeRequestID() {
        return oldElemOutRangeRequestID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", newElemInRangeRequestID=" + newElemInRangeRequestID +
                ", oldElemOutRangeRequestID=" + oldElemOutRangeRequestID +
                '}';
    }
}