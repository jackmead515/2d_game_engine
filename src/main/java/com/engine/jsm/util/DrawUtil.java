package com.engine.jsm.util;

import java.awt.*;

public class DrawUtil {

    public static void drawRect(Graphics2D g2, double[] rect) {
        g2.drawRect((int)rect[0], (int)rect[1], (int)rect[2], (int)rect[3]);
    }

}
