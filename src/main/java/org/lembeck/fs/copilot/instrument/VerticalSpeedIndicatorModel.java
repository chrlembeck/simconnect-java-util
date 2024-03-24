package org.lembeck.fs.copilot.instrument;

import java.util.ArrayList;
import java.util.List;

public class VerticalSpeedIndicatorModel {

    private final List<VerticalSpeedChangeListener> listeners = new ArrayList<>();

    private double verticalSpeedFeetPerMinute;

    private double verticalSpeedAutopilotFeetPerMinute;

    public void addChangeListener(VerticalSpeedChangeListener listener) {
        listeners.add(listener);
    }
    public void removeChangeListener(VerticalSpeedChangeListener listener) {
        listeners.remove(listener);
    }

    public double getVerticalSpeedFeetPerMinute() {
        return verticalSpeedFeetPerMinute;
    }

    public void setVerticalSpeedFeetPerMinute(double verticalSpeedFeetPerMinute) {
        this.verticalSpeedFeetPerMinute = verticalSpeedFeetPerMinute;
        for (int i = listeners.size() - 1; i >= 0; i--) {
            listeners.get(i).verticalSpeedChanged(this.verticalSpeedFeetPerMinute);
        }
    }

    public double getVerticalSpeedAutopilotFeetPerMinute() {
        return verticalSpeedAutopilotFeetPerMinute;
    }

    public void setVerticalSpeedAutopilot(double verticalSpeedAutopilot, boolean fireEvents) {
        this.verticalSpeedAutopilotFeetPerMinute = verticalSpeedAutopilot;
        if (fireEvents) {
            for (int i = listeners.size() - 1; i >= 0; i--) {
                listeners.get(i).verticalSpeedAutopilitChanged(this.verticalSpeedAutopilotFeetPerMinute);
            }
        }
    }
}
