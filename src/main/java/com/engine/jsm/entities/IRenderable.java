package com.engine.jsm.entities;

import java.awt.*;

public interface IRenderable {

    void render(Graphics2D g2, Canvas reference);

    void renderDebug(Graphics2D g2, Canvas reference);

}
