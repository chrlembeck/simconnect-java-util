package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.constants.FacilityListType;
import java.nio.ByteBuffer;

/**
 * The SimConnect_UnsubscribeToFacilities_EX1 function is used to request that notifications of additions to the
 * facilities cache are no longer sent, with the ability to specify which event should be disabled.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities_EX1.htm</a>
 */
public class UnsubscribeToFacilitiesEx1Request extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    private static final int TYPE_ID = 0xf0000048;

    private final FacilityListType type;
    private final boolean unsubscribeNewInRange;
    private final boolean unsubscribeOldOutRange;

    UnsubscribeToFacilitiesEx1Request(ByteBuffer buffer) {
        super(buffer);
        type = FacilityListType.ofId(buffer.getInt());
        unsubscribeNewInRange = buffer.get() != 0;
        unsubscribeOldOutRange = buffer.get() != 0;
    }

    /**
     * The SimConnect_UnsubscribeToFacilities_EX1 function is used to request that notifications of additions to the
     * facilities cache are no longer sent, with the ability to specify which event should be disabled.
     *
     * @param type                   Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param unsubscribeNewInRange  Specifies that the "new element in range" event should be disabled.
     * @param unsubscribeOldOutRange Specifies that the "element out of range" event should be disabled.
     */
    public UnsubscribeToFacilitiesEx1Request(FacilityListType type, boolean unsubscribeNewInRange,
            boolean unsubscribeOldOutRange) {
        super(TYPE_ID);
        this.type = type;
        this.unsubscribeNewInRange = unsubscribeNewInRange;
        this.unsubscribeOldOutRange = unsubscribeOldOutRange;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(type.ordinal());
        outBuffer.put(unsubscribeNewInRange ? (byte) 1 : (byte) 0);
        outBuffer.put(unsubscribeOldOutRange ? (byte) 1 : (byte) 0);
    }

    /**
     * Returns the type of facilities no further additions to the cache should be sent for.
     *
     * @return The type of facilities no further additions to the cache should be sent for.
     */
    public FacilityListType getType() {
        return type;
    }

    /**
     * Returns whether the "new element in range" event should be disabled.
     *
     * @return Specifies that the "new element in range" event should be disabled.
     */
    public boolean isUnsubscribeNewInRange() {
        return unsubscribeNewInRange;
    }

    /**
     * Returns whether the "element out of range" event should be disabled.
     *
     * @return Specifies that the "element out of range" event should be disabled.
     */
    public boolean isUnsubscribeOldOutRange() {
        return unsubscribeOldOutRange;
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
                ", unsubscribeNewInRange=" + unsubscribeNewInRange +
                ", unsubscribeOldOutRange=" + unsubscribeOldOutRange +
                '}';
    }
}