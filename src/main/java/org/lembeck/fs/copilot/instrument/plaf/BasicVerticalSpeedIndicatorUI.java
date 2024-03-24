package org.lembeck.fs.copilot.instrument.plaf;

import org.lembeck.fs.copilot.instrument.VerticalSpeedIndicator;
import org.lembeck.fs.copilot.instrument.VerticalSpeedIndicatorModel;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.GlyphVector;
import java.awt.geom.*;

import static org.lembeck.fs.copilot.GraphicsUtil.initGraphics;

public class BasicVerticalSpeedIndicatorUI extends VerticalSpeedIndicatorUI implements MouseWheelListener {

    private final int BORDER_WIDTH = 2;

    private final float GAP_WIDTH = 4;

    public static ComponentUI createUI(JComponent comp) {
        return new BasicVerticalSpeedIndicatorUI();
    }

    @Override
    public void installUI(JComponent c) {
        c.setOpaque(false);
        c.setFont(new Font("SansSerif", Font.BOLD, 18));
        c.addMouseWheelListener(this);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(200, 200);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        paint((Graphics2D) g, (VerticalSpeedIndicator) c);
    }

    private void paint(Graphics2D g, VerticalSpeedIndicator indicator) {
        initGraphics(g);
        VerticalSpeedIndicatorModel model = indicator.getModel();
        final int vsMax = 20;
        final double verticalSpeed = Math.max(-vsMax, Math.min(model.getVerticalSpeedFeetPerMinute() / 100, vsMax));
        final double verticalSpeedAutopilot = Math.max(-vsMax, Math.min(model.getVerticalSpeedAutopilotFeetPerMinute() / 100., vsMax));

        final Insets insets = indicator.getInsets();
        final float width = indicator.getWidth() - insets.left - insets.right;
        final float height = indicator.getHeight() - insets.top - insets.bottom;
        final float centerX = width / 2 + insets.left;
        final float centerY = height / 2 + insets.top;
        final float radius = Math.min(width, height) / 2 - BORDER_WIDTH;
        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        Shape background = new Ellipse2D.Float(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g.fill(background);
        g.setPaint(Color.LIGHT_GRAY);
        g.draw(background);
        Font font = indicator.getFont();
        g.setFont(font);
        int scalaLength = 12;
        final int rightSideGap = 20;
        final int ticks = 4;
        final int ticksBetween = 4;
        float degreePer100 = (360 - rightSideGap) / (2f * vsMax);
        float degreePerTick = (180 - rightSideGap / 2f) / ticks;
        float degreePerTickBetween = (180 - rightSideGap / 2f) / ticks / (ticksBetween + 1);
        Shape line = new Line2D.Double(centerX - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerY, centerX - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength, centerY);
        Shape betweenLine = new Line2D.Double(centerX - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerY, centerX - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength / 2, centerY);
        for (int i = 0; i <= ticks; i++) {
            g.setStroke(new BasicStroke(4));
            AffineTransform atUp = AffineTransform.getRotateInstance(i * degreePerTick * Math.PI / 180, centerX, centerY);
            AffineTransform atDown = AffineTransform.getRotateInstance(-i * degreePerTick * Math.PI / 180, centerX, centerY);
            g.draw(atUp.createTransformedShape(line));
            g.draw(atDown.createTransformedShape(line));

            GlyphVector vector = font.createGlyphVector(g.getFontRenderContext(), Integer.toString(i * vsMax / ticks));
            Shape shape = vector.getOutline();
            Rectangle2D bounds = shape.getBounds2D();
            AffineTransform toCenter = AffineTransform.getTranslateInstance(centerX - bounds.getCenterX(), centerY - bounds.getCenterY());
            AffineTransform rotateLeft = AffineTransform.getRotateInstance(i * degreePerTick * Math.PI / 180, centerX, centerY);
            AffineTransform toBorder = AffineTransform.getTranslateInstance(-radius + BORDER_WIDTH + scalaLength + GAP_WIDTH + (bounds.getWidth() + bounds.getHeight()) / 2, 0);
            AffineTransform rotateRight = AffineTransform.getRotateInstance(-i * degreePerTick * Math.PI / 180, centerX, centerY);
            AffineTransform aPos = new AffineTransform();
            aPos.concatenate(toCenter);
            aPos.concatenate(rotateLeft);
            aPos.concatenate(toBorder);
            aPos.concatenate(rotateRight);
            AffineTransform aNeg = new AffineTransform();
            aNeg.concatenate(toCenter);
            aNeg.concatenate(rotateRight);
            aNeg.concatenate(toBorder);
            aNeg.concatenate(rotateLeft);

            g.fill(aPos.createTransformedShape(shape));
            g.fill(aNeg.createTransformedShape(shape));

            if (i < ticks) {
                for (int j = 1; j <= ticksBetween; j++) {
                    g.setStroke(new BasicStroke(2));
                    AffineTransform atUp2 = AffineTransform.getRotateInstance((i * degreePerTick + j * degreePerTickBetween) * Math.PI / 180, centerX, centerY);
                    AffineTransform atDown2 = AffineTransform.getRotateInstance((-i * degreePerTick - j * degreePerTickBetween) * Math.PI / 180, centerX, centerY);
                    g.draw(atUp2.createTransformedShape(betweenLine));
                    g.draw(atDown2.createTransformedShape(betweenLine));
                }
            }
        }

        // Pfeil
        //Shape ind = new Line2D.Double(centerX - radius + BORDER_WIDTH + scalaLength + GAP_WIDTH, centerY, centerX, centerY);
        int arrowWidth = 4;
        Path2D ind = new Path2D.Float();
        float arrowLength = radius - BORDER_WIDTH - scalaLength - GAP_WIDTH;
        ind.moveTo(centerX, centerY + arrowWidth / 2);
        ind.lineTo(centerX - arrowLength * 3 / 4 + GAP_WIDTH, centerY +   arrowWidth);
        ind.lineTo(centerX - arrowLength, centerY);
        ind.lineTo(centerX - arrowLength * 3 / 4 + GAP_WIDTH, centerY -   arrowWidth);
        ind.lineTo(centerX, centerY - arrowWidth / 2);
        ind.lineTo(centerX, centerY + arrowWidth / 2);

        AffineTransform at = AffineTransform.getRotateInstance(degreePer100 * verticalSpeed * Math.PI / 180, centerX, centerY);

        g.setPaint(new Color(220, 220, 220));
        g.setStroke(new BasicStroke(3));
        g.fill(at.createTransformedShape(ind));


        // Autopilot
        int bugWidth = 16;
        int bugHeight = 14;
        Path2D bug = new Path2D.Float();

        bug.moveTo(centerX + BORDER_WIDTH - radius, centerY - bugWidth / 2);
        bug.lineTo(centerX + BORDER_WIDTH - radius + bugHeight, centerY - bugWidth / 2);
        bug.lineTo(centerX + BORDER_WIDTH - radius + BORDER_WIDTH, centerY - BORDER_WIDTH);
        bug.lineTo(centerX + BORDER_WIDTH - radius + BORDER_WIDTH, centerY + BORDER_WIDTH);
        bug.lineTo(centerX + BORDER_WIDTH - radius + bugHeight, centerY + bugWidth / 2);
        bug.lineTo(centerX + BORDER_WIDTH - radius, centerY + bugWidth / 2);
        bug.lineTo(centerX + BORDER_WIDTH - radius, centerY - bugWidth / 2);

        AffineTransform autoTransform = AffineTransform.getRotateInstance(degreePer100 * verticalSpeedAutopilot * Math.PI / 180, centerX, centerY);

        g.setColor(new Color(1, 159, 159));
        g.fill(autoTransform.createTransformedShape(bug));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        VerticalSpeedIndicator indicator = (VerticalSpeedIndicator) e.getSource();
        int delta = e.getWheelRotation();
        delta = Math.min(1, Math.max(-1, delta)) * 100;
        if (delta != 0) {
            VerticalSpeedIndicatorModel model = indicator.getModel();
            model.setVerticalSpeedAutopilot(Math.round((model.getVerticalSpeedAutopilotFeetPerMinute() - delta) / 100) * 100, true);
        }
    }
}