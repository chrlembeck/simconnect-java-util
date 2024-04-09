package org.lembeck.fs.copilot;

public class LoggingSimListener implements SimListener {

    @Override
    public void handlePlanePositionEvent(PlanePositionEvent planePositionEvent) {
//        System.out.println("handlePlanePositionEvent: " + planePositionEvent);
    }

    @Override
    public void handleAutopilotEvent(AutopilotEvent autopilotEvent) {
        System.out.println("handleAutopilotEvent: " + autopilotEvent);
    }

    @Override
    public void handleSwitchesEvent(SwitchesEvent switchesEvent) {
        System.out.println("handleSwitchesEvent: " + switchesEvent);
    }
}
