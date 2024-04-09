package org.lembeck.fs.copilot.instrument;

import java.util.ArrayList;
import java.util.List;

public class CompassModel {

    private final List<CompassChangeListener> listeners = new ArrayList<>();

    private float direction = 0;

    private float headingBug = 0;

    public void removeChangeListener(CompassChangeListener listener) {
        listeners.remove(listener);
    }

    public void addChangeListener(CompassChangeListener listener) {
        listeners.add(listener);
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = ((direction % 360) + 360) % 360;
        for (int i = listeners.size() - 1; i >= 0; i--) {
            listeners.get(i).directionChanged(this.direction);
        }
    }

    public float getHeadingBug() {
        return headingBug;
    }

    public void setHeadingBug(float headingBug, boolean fireEvents) {
        this.headingBug = ((headingBug % 360) + 360) % 360;
        if (fireEvents) {
            for (int i = listeners.size() - 1; i >= 0; i--) {
                listeners.get(i).headingBugChanged(this.headingBug);
            }
        }
    }
}