package org.lembeck.fs.copilot.instrument;

import org.lembeck.fs.copilot.instrument.plaf.BasicVerticalSpeedIndicatorUI;
import org.lembeck.fs.copilot.instrument.plaf.VerticalSpeedIndicatorUI;
import javax.swing.*;
import java.util.Objects;

public class VerticalSpeedIndicator extends JComponent implements VerticalSpeedChangeListener {

    static {
        UIManager.put(VerticalSpeedIndicator.uiClassID, BasicVerticalSpeedIndicatorUI.class.getName());
    }

    private VerticalSpeedIndicatorModel model;

    private static final String uiClassID = "org.lembeck.fs.VerticalSpeedIndicator";

    public VerticalSpeedIndicator() {
        super();
        init(new VerticalSpeedIndicatorModel());
    }

    private void init(VerticalSpeedIndicatorModel model) {
        setModel(model);
        updateUI();
    }

    public String getUIClassID() {
        return uiClassID;
    }

    public VerticalSpeedIndicatorModel getModel() {
        return model;
    }

    public void setModel(VerticalSpeedIndicatorModel newModel) {
        VerticalSpeedIndicatorModel oldModel = this.model;
        if (oldModel != null) {
            oldModel.removeChangeListener(this);
        }
        this.model = Objects.requireNonNull(newModel);
        this.model.addChangeListener(this);
        firePropertyChange("model", oldModel, newModel);
    }

    public void updateUI() {
        setUI(UIManager.getUI(this));
        invalidate();
    }

    @Override
    public void verticalSpeedChanged(double verticalSpeed) {
        repaint();
    }

    @Override
    public void verticalSpeedAutopilitChanged(double verticalSpeedAutopilot) {
        repaint();
    }
}