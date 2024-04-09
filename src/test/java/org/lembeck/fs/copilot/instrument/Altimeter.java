package org.lembeck.fs.copilot.instrument;

import org.lembeck.fs.copilot.instrument.plaf.AltimeterUI;
import org.lembeck.fs.copilot.instrument.plaf.BasicAltimeterUI;
import javax.swing.*;
import java.util.Objects;

public class Altimeter extends JComponent implements AltimeterChangeListener {

    static {
        UIManager.put(Altimeter.uiClassID, BasicAltimeterUI.class.getName());
    }

    private static final String uiClassID = "org.lembeck.fs.Altimeter";

    private AltimeterModel model;

    public Altimeter() {
        super();
        init(new AltimeterModel());
    }

    private void init(AltimeterModel model) {
        setModel(model);
        updateUI();
    }

    public String getUIClassID() {
        return uiClassID;
    }

    public AltimeterModel getModel() {
        return model;
    }

    public void setModel(AltimeterModel newModel) {
        AltimeterModel oldModel = this.model;
        if (oldModel != null) {
            oldModel.removeAltimeterChangeListener(this);
        }
        this.model = Objects.requireNonNull(newModel);
        this.model.addAltimeterChangeListener(this);
        firePropertyChange("model", oldModel, newModel);
    }

    public void updateUI() {
        setUI(UIManager.getUI(this));
        invalidate();
    }

    @Override
    public void altitudeChanged(double altitude) {
        repaint();
    }
}