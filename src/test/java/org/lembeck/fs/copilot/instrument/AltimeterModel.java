package org.lembeck.fs.copilot.instrument;

import java.util.ArrayList;
import java.util.List;

public class AltimeterModel {

    private final List<AltimeterChangeListener> listeners = new ArrayList<>();

    private double altitude;

    public void addAltimeterChangeListener(AltimeterChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeAltimeterChangeListener(AltimeterChangeListener listener) {
        this.listeners.remove(listener);
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
        for (int i = listeners.size() - 1; i >= 0; i--) {
            listeners.get(i).altitudeChanged(this.altitude);
        }
    }
}
