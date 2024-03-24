package org.lembeck.fs.copilot.instrument;

import org.lembeck.fs.copilot.instrument.plaf.BasicCompassUI;
import org.lembeck.fs.copilot.instrument.plaf.CompassUI;
import javax.swing.*;
import java.util.Objects;

public class Compass extends JComponent implements CompassChangeListener {

    static {
        UIManager.put(Compass.uiClassID, BasicCompassUI.class.getName());
    }

    private static final String uiClassID = "org.lembeck.fs.CompassUI";

    private CompassModel model;

    public Compass() {
        init(new CompassModel());
    }

    private void init(CompassModel model) {
        setModel(model);
        updateUI();
    }

    public String getUIClassID() {
        return uiClassID;
    }

    public CompassModel getModel() {
        return model;
    }

    public void setModel(CompassModel newModel) {
        CompassModel oldModel = this.model;
        if (oldModel != null) {
            oldModel.removeChangeListener(this);
        }
        this.model = Objects.requireNonNull(newModel);
        this.model.addChangeListener(this);
        firePropertyChange("model", oldModel, newModel);
    }

    public void updateUI() {
        setUI((CompassUI) UIManager.getUI(this));
        invalidate();
    }

    public CompassUI getUI() {
        return (CompassUI) ui;
    }

    public void setUI(CompassUI ui) {
        super.setUI(ui);
    }

    @Override
    public void directionChanged(float newDirection) {
        repaint();
    }

    @Override
    public void headingBugChanged(float headingBug) {
        repaint();
    }
}