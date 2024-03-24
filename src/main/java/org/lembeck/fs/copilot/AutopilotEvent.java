package org.lembeck.fs.copilot;

public class AutopilotEvent {
    private final boolean autopilotMaster;
    private final boolean headingLock;
    private final double headingLockVar;
    private final boolean nav1Lock;
    private final boolean verticalHold;
    private final double verticalHoldVar;
    private final boolean altitudeHold;
    private final double altitudeHoldVar;

    public AutopilotEvent(boolean autopilotMaster, boolean headingLock, double headingLockVar, boolean nav1Lock,
            boolean verticalHold, double verticalHoldVar, boolean altitudeHold, double altitudeHoldVar) {
        this.autopilotMaster = autopilotMaster;
        this.headingLock = headingLock;
        this.headingLockVar = headingLockVar;
        this.nav1Lock = nav1Lock;
        this.verticalHold = verticalHold;
        this.verticalHoldVar = verticalHoldVar;
        this.altitudeHold = altitudeHold;
        this.altitudeHoldVar = altitudeHoldVar;
    }

    public boolean isAutopilotMaster() {
        return autopilotMaster;
    }

    public boolean isHeadingLock() {
        return headingLock;
    }

    public double getHeadingLockVar() {
        return headingLockVar;
    }

    public boolean isNav1Lock() {
        return nav1Lock;
    }

    public boolean isVerticalHold() {
        return verticalHold;
    }

    public double getVerticalHoldVar() {
        return verticalHoldVar;
    }

    public boolean isAltitudeHold() {
        return altitudeHold;
    }

    public double getAltitudeHoldVar() {
        return altitudeHoldVar;
    }

    @Override
    public String toString() {
        return "AutopilotEvent{" +
                "autopilotMaster=" + autopilotMaster +
                ", headingLock=" + headingLock +
                ", headingLockVar=" + headingLockVar +
                ", nav1Lock=" + nav1Lock +
                ", verticalHold=" + verticalHold +
                ", verticalHoldVar=" + verticalHoldVar +
                ", altitudeHold=" + altitudeHold +
                ", altitudeHoldVar=" + altitudeHoldVar +
                '}';
    }
}
