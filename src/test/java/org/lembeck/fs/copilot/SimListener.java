package org.lembeck.fs.copilot;

public interface SimListener {
    void handlePlanePositionEvent(PlanePositionEvent planePositionEvent);

    void handleAutopilotEvent(AutopilotEvent autopilotEvent);

    void handleSwitchesEvent(SwitchesEvent switchesEvent);
}
