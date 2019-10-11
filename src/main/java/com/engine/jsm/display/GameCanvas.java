package com.engine.jsm.display;

import com.engine.jsm.main.Main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

	public GameCanvas() {}

	public void paint() {
		BufferStrategy bs = this.getBufferStrategy();
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		Main.world.render(g2, this);

		bs.show();
		g2.dispose();
	}
}
