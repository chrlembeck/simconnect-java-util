package org.lembeck.fs.copilot.instrument;

public interface CompassChangeListener {

    void directionChanged(float newDirection);

    void headingBugChanged(float headingBug);
}
