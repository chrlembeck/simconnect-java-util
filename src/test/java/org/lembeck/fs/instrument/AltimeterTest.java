package org.lembeck.fs.instrument;

import org.lembeck.fs.copilot.instrument.Altimeter;
import org.lembeck.fs.copilot.instrument.Compass;
import javax.swing.*;
import java.awt.*;

public class AltimeterTest extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AltimeterTest().setVisible(true);
        });
    }

    private AltimeterTest() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        Altimeter altimeter = new Altimeter();
        altimeter.getModel().setAltitude(2300);
        altimeter.setSize(200, 200);
        getContentPane().add(altimeter, BorderLayout.CENTER);
        pack();
    }
}
