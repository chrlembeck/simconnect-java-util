package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;
import java.nio.ByteBuffer;

/**
 * The SIMCONNECT_JETWAY_DATA structure is used to return information on a single jetway.
 */
public class JetwayData {

    /**
     * ICAO code of the airport (will be the same as the one you used to make the request).
     */
    private final String icao;

    /**
     * Index of the parking space linked to this jetway (will be the same as the one you used to make the request).
     */
    private final int parkingIndex;

    /**
     * Lattitude / Longitude / Altitude of the jetway, returned as a SIMCONNECT_DATA_LATLONALT struct.
     */
    private final LatLonAlt lla;

    /**
     * Pitch / Bank / Heading of the jetway, returned as a SIMCONNECT_DATA_PBH struct.
     */
    private final PitchBankHeading pbh;

    /**
     * The status of the jetway. This will be one of the following:
     * <dl>
     *     <dt>0</dt><dd>JETWAY_STATUS_REST</dd>
     *     <dt>1</dt><dd>JETWAY_STATUS_APPROACH_OUTSIDE</dd>
     *     <dt>2</dt><dd>JETWAY_STATUS_APPROACH_DOOR</dd>
     *     <dt>3</dt><dd>JETWAY_STATUS_HOOD_CONNECT</dd>
     *     <dt>4</dt><dd>JETWAY_STATUS_HOOD_DISCONNECT</dd>
     *     <dt>5</dt><dd>JETWAY_STATUS_RETRACT_OUTSIDE</dd>
     *     <dt>6</dt><dd>JETWAY_STATUS_RETRACT_HOME</dd>
     *     <dt>7</dt><dd>JETWAY_STATUS_FULLY_ATTACHED</dd>
     * </dl>
     */
    private final int status;

    /**
     * The index of the door attached to the jetway.
     */
    private final int door;

    /**
     * Door position relative to aircraft, returned as a SIMCONNECT_DATA_XYZ struct.
     */
    private final DataXYZ exitDoorRelativePos;

    /**
     * Relative position of IK_MainHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    private final DataXYZ mainHandlePos;

    /**
     * Relative position of IK_SecondaryHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    private final DataXYZ secondaryHandlePos;

    /**
     * Relative position of IK_WheelsGroundLock (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    private final DataXYZ wheelGroundLock;

    /**
     * ObjectId of the jetway (used by SimConnect_RequestDataOnSimObject for example).
     */
    private final int jetwayObjectID;

    /**
     * ObjectId of the object (aircraft) attached to the jetway (used by SimConnect_RequestDataOnSimObject for example).
     */
    private final int attachedObjectID;

    JetwayData(ByteBuffer buffer) {
        icao = SimUtil.readString(buffer, 8);
        parkingIndex = buffer.getInt();
        lla = new LatLonAlt(buffer);
        pbh = new PitchBankHeading(buffer);
        status = buffer.getInt();
        door = buffer.getInt();
        exitDoorRelativePos = new DataXYZ(buffer);
        mainHandlePos = new DataXYZ(buffer);
        secondaryHandlePos = new DataXYZ(buffer);
        wheelGroundLock = new DataXYZ(buffer);
        jetwayObjectID = buffer.getInt();
        attachedObjectID = buffer.getInt();
    }

    /**
     * Creates a new jetway data object.
     *
     * @param icao                ICAO code of the airport (will be the same as the one you used to make the request).
     * @param parkingIndex        Index of the parking space linked to this jetway (will be the same as the one you used to make the request).
     * @param lla                 Lattitude / Longitude / Altitude of the jetway, returned as a SIMCONNECT_DATA_LATLONALT struct.
     * @param pbh                 Pitch / Bank / Heading of the jetway, returned as a SIMCONNECT_DATA_PBH struct.
     * @param status              The status of the jetway, which will be one of the following:
     *                            <dl>
     *                            <dt>0</dt><dd>JETWAY_STATUS_REST</dd>
     *                            <dt>1</dt><dd>JETWAY_STATUS_APPROACH_OUTSIDE</dd>
     *                            <dt>2</dt><dd>JETWAY_STATUS_APPROACH_DOOR</dd>
     *                            <dt>3</dt><dd>JETWAY_STATUS_HOOD_CONNECT</dd>
     *                            <dt>4</dt><dd>JETWAY_STATUS_HOOD_DISCONNECT</dd>
     *                            <dt>5</dt><dd>JETWAY_STATUS_RETRACT_OUTSIDE</dd>
     *                            <dt>6</dt><dd>JETWAY_STATUS_RETRACT_HOME</dd>
     *                            <dt>7</dt><dd>JETWAY_STATUS_FULLY_ATTACHED</dd>
     *                            </dl>
     * @param door                The index of the door attached to the jetway.
     * @param exitDoorRelativePos Door position relative to aircraft, returned as a SIMCONNECT_DATA_XYZ struct.
     * @param mainHandlePos       Relative position of IK_MainHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     * @param secondaryHandlePos  Relative position of IK_SecondaryHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     * @param wheelGroundLock     Relative position of IK_WheelsGroundLock (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     * @param jetwayObjectID      ObjectId of the jetway (used by SimConnect_RequestDataOnSimObject for example).
     * @param attachedObjectID    ObjectId of the object (aircraft) attached to the jetway (used by SimConnect_RequestDataOnSimObject for example).
     */
    public JetwayData(String icao, int parkingIndex, LatLonAlt lla, PitchBankHeading pbh, int status, int door,
            DataXYZ exitDoorRelativePos, DataXYZ mainHandlePos, DataXYZ secondaryHandlePos, DataXYZ wheelGroundLock,
            int jetwayObjectID, int attachedObjectID) {
        this.icao = icao;
        this.parkingIndex = parkingIndex;
        this.lla = lla;
        this.pbh = pbh;
        this.status = status;
        this.door = door;
        this.exitDoorRelativePos = exitDoorRelativePos;
        this.mainHandlePos = mainHandlePos;
        this.secondaryHandlePos = secondaryHandlePos;
        this.wheelGroundLock = wheelGroundLock;
        this.jetwayObjectID = jetwayObjectID;
        this.attachedObjectID = attachedObjectID;
    }

    /**
     * Returns the ICAO code of the airport (will be the same as the one you used to make the request).
     *
     * @return ICAO code of the airport (will be the same as the one you used to make the request).
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Returns the Index of the parking space linked to this jetway (will be the same as the one you used to make the request).
     *
     * @return Index of the parking space linked to this jetway (will be the same as the one you used to make the request).
     */
    public int getParkingIndex() {
        return parkingIndex;
    }

    /**
     * Returns the Lattitude / Longitude / Altitude of the jetway, returned as a SIMCONNECT_DATA_LATLONALT struct.
     *
     * @return Lattitude / Longitude / Altitude of the jetway, returned as a SIMCONNECT_DATA_LATLONALT struct.
     */
    public LatLonAlt getLla() {
        return lla;
    }

    /**
     * Returns the Pitch / Bank / Heading of the jetway, returned as a SIMCONNECT_DATA_PBH struct.
     *
     * @return Pitch / Bank / Heading of the jetway, returned as a SIMCONNECT_DATA_PBH struct.
     */
    public PitchBankHeading getPbh() {
        return pbh;
    }

    /**
     * Returns the status of the jetway.
     * <dl>
     * <dt>0</dt><dd>JETWAY_STATUS_REST</dd>
     * <dt>1</dt><dd>JETWAY_STATUS_APPROACH_OUTSIDE</dd>
     * <dt>2</dt><dd>JETWAY_STATUS_APPROACH_DOOR</dd>
     * <dt>3</dt><dd>JETWAY_STATUS_HOOD_CONNECT</dd>
     * <dt>4</dt><dd>JETWAY_STATUS_HOOD_DISCONNECT</dd>
     * <dt>5</dt><dd>JETWAY_STATUS_RETRACT_OUTSIDE</dd>
     * <dt>6</dt><dd>JETWAY_STATUS_RETRACT_HOME</dd>
     * <dt>7</dt><dd>JETWAY_STATUS_FULLY_ATTACHED</dd>
     * </dl>
     *
     * @return The status of the jetway.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the index of the door attached to the jetway.
     *
     * @return The index of the door attached to the jetway.
     */
    public int getDoor() {
        return door;
    }

    /**
     * Returns the Door position relative to aircraft, returned as a SIMCONNECT_DATA_XYZ struct.
     *
     * @return Door position relative to aircraft, returned as a SIMCONNECT_DATA_XYZ struct.
     */
    public DataXYZ getExitDoorRelativePos() {
        return exitDoorRelativePos;
    }

    /**
     * Returns the Relative position of IK_MainHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     *
     * @return Relative position of IK_MainHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    public DataXYZ getMainHandlePos() {
        return mainHandlePos;
    }

    /**
     * Returns the Relative position of IK_SecondaryHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     *
     * @return Relative position of IK_SecondaryHandle (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    public DataXYZ getSecondaryHandlePos() {
        return secondaryHandlePos;
    }

    /**
     * Returns the Relative position of IK_WheelsGroundLock (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     *
     * @return Relative position of IK_WheelsGroundLock (world pos, in meters), returned as a SIMCONNECT_DATA_XYZ struct.
     */
    public DataXYZ getWheelGroundLock() {
        return wheelGroundLock;
    }

    /**
     * Returns the ObjectId of the jetway (used by SimConnect_RequestDataOnSimObject for example).
     *
     * @return ObjectId of the jetway (used by SimConnect_RequestDataOnSimObject for example).
     */
    public int getJetwayObjectID() {
        return jetwayObjectID;
    }

    /**
     * Returns the ObjectId of the object (aircraft) attached to the jetway (used by SimConnect_RequestDataOnSimObject for example).
     *
     * @return ObjectId of the object (aircraft) attached to the jetway (used by SimConnect_RequestDataOnSimObject for example).
     */
    public int getAttachedObjectID() {
        return attachedObjectID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "icao='" + icao + '\'' +
                ", parkingIndex=" + parkingIndex +
                ", lla=" + lla +
                ", pbh=" + pbh +
                ", status=" + status +
                ", door=" + door +
                ", exitDoorRelativePos=" + exitDoorRelativePos +
                ", mainHandlePos=" + mainHandlePos +
                ", secondaryHandlePos=" + secondaryHandlePos +
                ", wheelGroundLock=" + wheelGroundLock +
                ", jetwayObjectID=" + jetwayObjectID +
                ", attachedObjectID=" + attachedObjectID +
                '}';
    }
}