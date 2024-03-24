package org.lembeck.fs.copilot;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GraphicsUtil {

    public static final NumberFormat DECIMAL_0 = DecimalFormat.getInstance();
    public static final NumberFormat DECIMAL_2 = DecimalFormat.getInstance();
    public static final NumberFormat DECIMAL_3 = DecimalFormat.getInstance();
    public static final NumberFormat DECIMAL_8 = DecimalFormat.getInstance();

    static {
        DECIMAL_0.setMaximumFractionDigits(0);
        DECIMAL_2.setMaximumFractionDigits(2);
        DECIMAL_2.setMinimumFractionDigits(2);
        DECIMAL_3.setMaximumFractionDigits(3);
        DECIMAL_3.setMinimumFractionDigits(3);
        DECIMAL_8.setMaximumFractionDigits(8);
        DECIMAL_8.setMinimumFractionDigits(8);
    }

    public static void initGraphics(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }


}
