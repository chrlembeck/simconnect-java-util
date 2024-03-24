package org.lembeck.fs.copilot.instrument;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.lembeck.fs.copilot.GraphicsUtil.initGraphics;

public class LedIcon implements Icon {

    private final int size;

    private final Color color;

    private final Color borderColor;

    public LedIcon(int size, Color color, Color borderColor) {
        this.size = size;
        this.color = color;
        this.borderColor = borderColor;
    }

    @Override
    public void paintIcon(Component c, Graphics graphics, int x, int y) {
        Graphics2D g = (Graphics2D) graphics;
        initGraphics(g);
        g.setStroke(new BasicStroke(1));
        Ellipse2D circle = new Ellipse2D.Float(x + 0.5f, y + 0.5f, size - 1, size - 1);
        Paint paint = new GradientPaint(x+0.2f*size, y+0.2f*size, color.brighter(), x+0.8f*size, y+0.8f*size, color.darker());
        g.setPaint(paint);
        g.fill(circle);
        g.setColor(borderColor);
        g.draw(circle);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
