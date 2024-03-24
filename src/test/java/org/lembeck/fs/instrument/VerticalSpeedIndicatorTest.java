package org.lembeck.fs.instrument;

import org.lembeck.fs.copilot.instrument.VerticalSpeedIndicator;
import javax.swing.*;
import java.awt.*;

public class VerticalSpeedIndicatorTest extends JFrame {

    public static void main(String[] args) {
        int i = -100;
        short v = (short)i;

        System.out.println(v + " " + Integer.toBinaryString(v));
        System.out.println((v&0xffff) + " " + Integer.toBinaryString(v & 0xffff));

        SwingUtilities.invokeLater(() -> {
            new VerticalSpeedIndicatorTest().setVisible(true);
        });
    }

    private VerticalSpeedIndicatorTest() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        VerticalSpeedIndicator indicator = new VerticalSpeedIndicator();
        indicator.getModel().setVerticalSpeedFeetPerMinute(7);
        indicator.getModel().setVerticalSpeedAutopilot(-13, true);
        indicator.setSize(200, 200);
        getContentPane().add(indicator, BorderLayout.CENTER);
        pack();
    }
}
