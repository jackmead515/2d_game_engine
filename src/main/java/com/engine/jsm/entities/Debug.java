package com.engine.jsm.entities;

import com.engine.jsm.main.Main;
import com.engine.jsm.main.Stats;

import java.awt.*;

public class Debug implements IRenderable {

    @Override
    public void render(Graphics2D g2, Canvas reference) {
        double[] vp = Main.world.getCamera().getViewPort();
        double[] c = Stats.getScreenCenter();

        g2.setColor(Color.GREEN);
        g2.drawString("FPS: " + Stats.getFps(), (int)vp[0]+32, (int)vp[1]+32);

        g2.drawLine((int)c[0]-5, (int)c[1], (int)c[0]+5, (int)c[1]);
        g2.drawLine((int)c[0], (int)c[1]-5, (int)c[0], (int)c[1]+5);

        g2.drawRect(
            (int)vp[0]+10,
            (int)vp[1]+10,
            (int)vp[2]-20,
            (int)vp[3]-20
        );
    }
}
