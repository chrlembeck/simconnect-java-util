package org.lembeck.fs.copilot.instrument.plaf;

import org.lembeck.fs.copilot.instrument.Compass;
import org.lembeck.fs.copilot.instrument.CompassModel;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.GlyphVector;
import java.awt.geom.*;

import static org.lembeck.fs.copilot.GraphicsUtil.initGraphics;

public class BasicCompassUI extends CompassUI implements MouseWheelListener {

    private final float BORDER_WIDTH = 2;

    private final float GAP_WIDTH = 4;

    public static ComponentUI createUI(JComponent comp) {
        return new BasicCompassUI();
    }

    @Override
    public void installUI(JComponent c) {
        c.setOpaque(false);
        c.setFont(new Font("SansSerif", Font.BOLD, 14));
        c.addMouseWheelListener(this);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.removeMouseWheelListener(this);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(200, 200);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        paint((Graphics2D) g, (Compass) c);
    }

    private void paint(Graphics2D g, Compass compass) {
        initGraphics(g);
        CompassModel model = compass.getModel();
        final float direction = model.getDirection();

        final Insets insets = compass.getInsets();
        final float width = compass.getWidth() - insets.left - insets.right;
        final float height = compass.getHeight() - insets.top - insets.bottom;
        final float centerX = width / 2 + insets.left;
        final float centerY = height / 2 + insets.top;
        final float radius = Math.min(width, height) / 2 - BORDER_WIDTH;
        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        Shape background = new Ellipse2D.Float(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g.fill(background);
        g.setPaint(Color.LIGHT_GRAY);
        g.draw(background);
        Font font = compass.getFont();
        g.setFont(font);
        int scalaLength = 12;

        Shape line = new Line2D.Float(centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength);
        Shape halfLine = new Line2D.Float(centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength / 2);
        for (int i = 0; i < 36; i++) {
            AffineTransform at = AffineTransform.getRotateInstance((i * 10 - direction) * Math.PI / 180, centerX, centerY);
            g.draw(at.createTransformedShape(line));
            AffineTransform at2 = AffineTransform.getRotateInstance((i * 10 - direction + 5) * Math.PI / 180, centerX, centerY);
            g.draw(at2.createTransformedShape(halfLine));
        }

        Font biggerFont = font.deriveFont(font.getSize2D() * 1.3f);
        for (int i = 0; i < 12; i++) {
            Font cFont = i%3 ==0?biggerFont:font;
            GlyphVector vector = cFont.createGlyphVector(g.getFontRenderContext(), calcText(i * 3));

            float absDir = -direction + i * 30;
            Shape shape = vector.getOutline();
            Rectangle2D bounds = shape.getBounds2D();
            AffineTransform at = AffineTransform.getTranslateInstance(centerX - bounds.getCenterX(), centerY - bounds.getCenterY() - radius + bounds.getHeight() + BORDER_WIDTH + scalaLength + GAP_WIDTH);
            AffineTransform at2 = AffineTransform.getRotateInstance(absDir * Math.PI / 180, centerX, centerY);
            shape = at.createTransformedShape(shape);
            shape = at2.createTransformedShape(shape);
            g.setPaint(i % 3 == 0 ? new Color(240,220,0) : Color.LIGHT_GRAY);
            g.fill(shape);
        }

        // Heading Indicator
        g.setColor(Color.YELLOW);
        g.draw(new Line2D.Float(centerX, centerY - radius + BORDER_WIDTH, centerX, centerY - radius + BORDER_WIDTH + GAP_WIDTH + scalaLength));

        int bugWidth = 16;
        int bugHeight = 14;
        Path2D bug = new Path2D.Float();

        bug.moveTo(centerX - bugWidth / 2, centerY + BORDER_WIDTH - radius);
        bug.lineTo(centerX - bugWidth / 2, centerY + BORDER_WIDTH - radius + bugHeight);
        bug.lineTo(centerX - BORDER_WIDTH, centerY + BORDER_WIDTH - radius + BORDER_WIDTH);
        bug.lineTo(centerX + BORDER_WIDTH, centerY + BORDER_WIDTH - radius + BORDER_WIDTH);
        bug.lineTo(centerX + bugWidth / 2, centerY + BORDER_WIDTH - radius + bugHeight);
        bug.lineTo(centerX + bugWidth / 2, centerY + BORDER_WIDTH - radius);
        bug.lineTo(centerX - bugWidth / 2, centerY + BORDER_WIDTH - radius);
        g.setColor(new Color(0, 200, 200));
        AffineTransform at = AffineTransform.getRotateInstance((-direction + model.getHeadingBug()) * Math.PI / 180, centerX, centerY);
        g.fill(at.createTransformedShape(bug));
    }

    private String calcText(int direction) {
        return switch (direction) {
            case 0 -> "N";
            case 9 -> "E";
            case 18 -> "S";
            case 27 -> "W";
            default -> Integer.toString(direction);
        };
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Compass compass = (Compass) e.getSource();
        int delta = e.getWheelRotation();
        if (delta != 0) {
            CompassModel model = compass.getModel();
            model.setHeadingBug(model.getHeadingBug() - delta, true);
        }
    }
}