package org.lembeck.fs.copilot.instrument.plaf;

import org.lembeck.fs.copilot.instrument.Altimeter;
import org.lembeck.fs.copilot.instrument.AltimeterModel;
import org.lembeck.fs.copilot.instrument.Compass;
import org.lembeck.fs.copilot.instrument.CompassModel;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.*;

import static org.lembeck.fs.copilot.GraphicsUtil.initGraphics;

public class BasicAltimeterUI extends AltimeterUI {

    private final float BORDER_WIDTH = 2;

    private final float GAP_WIDTH = 4;


    public static ComponentUI createUI(JComponent comp) {
        return new BasicAltimeterUI();
    }

    @Override
    public void installUI(JComponent c) {
        c.setOpaque(false);
        c.setFont(new Font("SansSerif", Font.BOLD, 16));
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
    }


    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(200, 200);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        paint((Graphics2D) g, (Altimeter) c);
    }

    private void paint(Graphics2D g, Altimeter altimeter) {
        initGraphics(g);
        AltimeterModel model = altimeter.getModel();
        final double altitude = model.getAltitude();

        final Insets insets = altimeter.getInsets();
        final float width = altimeter.getWidth() - insets.left - insets.right;
        final float height = altimeter.getHeight() - insets.top - insets.bottom;
        final float centerX = width / 2 + insets.left;
        final float centerY = height / 2 + insets.top;
        final float radius = Math.min(width, height) / 2 - BORDER_WIDTH;
        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        Shape background = new Ellipse2D.Float(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g.fill(background);
        g.setPaint(Color.LIGHT_GRAY);
        g.draw(background);
        Font font = altimeter.getFont();

        int scalaLength = 12;
        Shape line = new Line2D.Float(centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength);
        Shape halfLine = new Line2D.Float(centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH, centerX, centerY - radius + BORDER_WIDTH / 2 + GAP_WIDTH + scalaLength / 2);
        for (int i = 0; i < 10; i++) {
            g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            AffineTransform at = AffineTransform.getRotateInstance((i * 36) * Math.PI / 180, centerX, centerY);
            g.draw(at.createTransformedShape(line));
            g.setStroke(new BasicStroke(2));
            for (int j = 1; j < 5; j++) {
                AffineTransform at2 = AffineTransform.getRotateInstance((i * 36 + j * 7) * Math.PI / 180, centerX, centerY);
                g.draw(at2.createTransformedShape(halfLine));
            }

            GlyphVector vector = font.createGlyphVector(g.getFontRenderContext(), Integer.toString(i));
            Shape shape = vector.getOutline();
            Rectangle2D bounds = shape.getBounds2D();
            AffineTransform toCenter = AffineTransform.getTranslateInstance(centerX - bounds.getCenterX(), centerY - bounds.getCenterY());
            AffineTransform rotateLeft = AffineTransform.getRotateInstance((90 + i * 36) * Math.PI / 180, centerX, centerY);
            AffineTransform toBorder = AffineTransform.getTranslateInstance(-radius + BORDER_WIDTH + scalaLength + GAP_WIDTH + Math.max(bounds.getWidth(), bounds.getHeight()), 0);
            AffineTransform rotateRight = AffineTransform.getRotateInstance((-90 - i * 36) * Math.PI / 180, centerX, centerY);
            AffineTransform transform = new AffineTransform();
            transform.concatenate(toCenter);
            transform.concatenate(rotateLeft);
            transform.concatenate(toBorder);
            transform.concatenate(rotateRight);
            g.fill(transform.createTransformedShape(shape));

        }


        // 10000-er Pfeil
        Path2D p1000 = new Path2D.Double();
        float pSize = 17;
        p1000.moveTo(centerX-pSize/2, centerY-radius+BORDER_WIDTH);
        p1000.lineTo(centerX+pSize/2, centerY-radius+BORDER_WIDTH);
        p1000.lineTo(centerX, centerY-radius+BORDER_WIDTH+pSize);
        p1000.lineTo(centerX-pSize/2, centerY-radius-BORDER_WIDTH);
        double deg10000 = altitude*36 / 10000;
        AffineTransform at3 = AffineTransform.getRotateInstance(deg10000 * Math.PI / 180, centerX, centerY);

        g.setPaint(new Color(236, 15, 85));
        g.setStroke(new BasicStroke(3));
        g.fill(at3.createTransformedShape(p1000));


        // 1000-er-Pfeil
        //Shape ind = new Line2D.Double(centerX - radius + BORDER_WIDTH + scalaLength + GAP_WIDTH, centerY, centerX, centerY);
        int arrowWidth = 7;
        double deg1000 = 90 + (altitude / 1000) * 36;
        Path2D ind = new Path2D.Float();
        float arrowLength = (radius - BORDER_WIDTH - scalaLength - GAP_WIDTH) * 2 / 3;
        ind.moveTo(centerX, centerY + arrowWidth / 2);
        ind.lineTo(centerX - arrowLength * 3 / 4 + GAP_WIDTH, centerY + arrowWidth);
        ind.lineTo(centerX - arrowLength, centerY);
        ind.lineTo(centerX - arrowLength * 3 / 4 + GAP_WIDTH, centerY - arrowWidth);
        ind.lineTo(centerX, centerY - arrowWidth / 2);
        ind.lineTo(centerX, centerY + arrowWidth / 2);

        AffineTransform at = AffineTransform.getRotateInstance(deg1000 * Math.PI / 180, centerX, centerY);

        g.setPaint(new Color(220, 220, 220));
        g.setStroke(new BasicStroke(3));
        g.fill(at.createTransformedShape(ind));

        // 100-er-Pfeil
        //Shape ind = new Line2D.Double(centerX - radius + BORDER_WIDTH + scalaLength + GAP_WIDTH, centerY, centerX, centerY);
        int arrowWidth2 = 3;
        double deg100 = 90 + (altitude % 1000) * 360 / 1000;
        Path2D ind2 = new Path2D.Float();
        float arrowLength2 = radius - BORDER_WIDTH - scalaLength - GAP_WIDTH;
        ind2.moveTo(centerX, centerY + arrowWidth2 / 2);
        ind2.lineTo(centerX - arrowLength2 * 3 / 4 + GAP_WIDTH, centerY + arrowWidth2);
        ind2.lineTo(centerX - arrowLength2, centerY);
        ind2.lineTo(centerX - arrowLength2 * 3 / 4 + GAP_WIDTH, centerY - arrowWidth2);
        ind2.lineTo(centerX, centerY - arrowWidth2 / 2);
        ind2.lineTo(centerX, centerY + arrowWidth2 / 2);

        AffineTransform at2 = AffineTransform.getRotateInstance(deg100 * Math.PI / 180, centerX, centerY);

        g.setPaint(new Color(220, 220, 220));
        g.setStroke(new BasicStroke(3));
        g.fill(at2.createTransformedShape(ind2));


    }
}