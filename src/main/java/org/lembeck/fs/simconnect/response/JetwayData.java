package org.lembeck.fs.simconnect.response;

import org.lembeck.fs.simconnect.SimUtil;

import java.nio.ByteBuffer;

public class JetwayData {

    private final String icao;

    private final int parkingIndex;
    private final LatLonAlt lla;
    private final PitchBankHeading pbh;
    private final int status;
    private final int door;
    private final DataXYZ exitDoorRelativePos;
    private final DataXYZ mainHandlePos;
    private final DataXYZ secondaryHandlePos;
    private final DataXYZ wheelGroundLock;
    private final int jetwayObjectID;
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

    public JetwayData(String icao, int parkingIndex, LatLonAlt lla, PitchBankHeading pbh, int status, int door, DataXYZ exitDoorRelativePos, DataXYZ mainHandlePos, DataXYZ secondaryHandlePos, DataXYZ wheelGroundLock, int jetwayObjectID, int attachedObjectID) {
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

    public String getIcao() {
        return icao;
    }

    public int getParkingIndex() {
        return parkingIndex;
    }

    public LatLonAlt getLla() {
        return lla;
    }

    public PitchBankHeading getPbh() {
        return pbh;
    }

    public int getStatus() {
        return status;
    }

    public int getDoor() {
        return door;
    }

    public DataXYZ getExitDoorRelativePos() {
        return exitDoorRelativePos;
    }

    public DataXYZ getMainHandlePos() {
        return mainHandlePos;
    }

    public DataXYZ getSecondaryHandlePos() {
        return secondaryHandlePos;
    }

    public DataXYZ getWheelGroundLock() {
        return wheelGroundLock;
    }

    public int getJetwayObjectID() {
        return jetwayObjectID;
    }

    public int getAttachedObjectID() {
        return attachedObjectID;
    }

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