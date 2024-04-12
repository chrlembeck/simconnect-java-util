package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SIMCONNECT_RECV_WAYPOINT_LIST structure is used to return a list of SIMCONNECT_DATA_FACILITY_WAYPOINT structures.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_WAYPOINT_LIST.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_RECV_WAYPOINT_LIST.htm</a>
 */
public class RecvWaypointListResponse extends RecvFacilitiesListResponse {

    /**
     * Array of SIMCONNECT_DATA_FACILITY_WAYPOINT structures.
     */
    protected final FacilityWaypoint[] waypointList;

    RecvWaypointListResponse(ByteBuffer buffer) {
        super(buffer);
        waypointList = new FacilityWaypoint[arraySize];
        for (int i = 0; i < arraySize; i++) {
            String icao = SimUtil.readString(buffer, 6);
            String regionCode = SimUtil.readString(buffer, 3);
            double latitude = buffer.getDouble();
            double longitude = buffer.getDouble();
            double altitude = buffer.getDouble();
            float magVar = buffer.getFloat();
            waypointList[i] = new FacilityWaypoint(icao, regionCode, latitude, longitude, altitude, magVar);
        }
    }

    /**
     * Returns the array of SIMCONNECT_DATA_FACILITY_WAYPOINT structures.
     *
     * @return Array of SIMCONNECT_DATA_FACILITY_WAYPOINT structures.
     */
    public FacilityWaypoint[] getWaypointList() {
        return waypointList;
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
                ", requestID=" + requestID +
                ", arraySize=" + arraySize +
                ", entryNumber=" + entryNumber +
                ", outOf=" + outOf +
                ", waypointList=" + Arrays.toString(waypointList) +
                "}";
    }
}