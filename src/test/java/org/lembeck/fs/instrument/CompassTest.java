package org.lembeck.fs.instrument;

import org.lembeck.fs.copilot.instrument.Compass;
import javax.swing.*;
import java.awt.*;

public class CompassTest extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CompassTest().setVisible(true);
        });
    }

    private CompassTest() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        Compass compass = new Compass();
        compass.getModel().setDirection(33);
        compass.getModel().setHeadingBug(120, true);
        compass.setSize(200, 200);
        getContentPane().add(compass, BorderLayout.CENTER);
        pack();
    }
}
