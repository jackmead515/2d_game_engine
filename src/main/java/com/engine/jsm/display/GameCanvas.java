package com.engine.jsm.display;

import com.engine.jsm.input.InputManager;
import com.engine.jsm.main.Main;
import com.engine.jsm.main.Stats;
import com.engine.jsm.util.GeomUtil;
import com.engine.jsm.util.NumberUtil;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Arrays;

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

		double[] cam = Main.world.getCamera().getPosition();
		g2.translate(-cam[0], -cam[1]);
		Main.world.render(g2, this);
		if (Stats.isDebug()) {
			Main.world.renderDebug(g2, this);
		}
		g2.translate(cam[0], cam[1]);
		Main.gui.render(g2, this);
		if (Stats.isDebug()) {
			Main.gui.renderDebug(g2, this);
			paintDebug(g2);
		}
		bs.show();
		g2.dispose();
	}

	public void paintDebug(Graphics2D g2) {
		double[] focus = Main.world.getCamera().getFocus();
		double[] mouse = InputManager.getMouse().getPosition();
		double[] capture = Main.world.getCamera().getCaptureWheel();
		double[] dimensions = Main.world.getCamera().getDimensions();
		double[] center = Stats.getScreenCenter();

		g2.setColor(Color.GREEN);
		g2.drawString("Focus: " + Arrays.toString(NumberUtil.toIntegers(focus)), 32, 32);
		g2.drawString("FPS: " + Stats.getFps(), 32, 44);

		g2.drawLine((int)center[0]-5, (int)center[1], (int)center[0]+5, (int)center[1]);
		g2.drawLine((int)center[0], (int)center[1]-5, (int)center[0], (int)center[1]+5);
		g2.drawOval((int)center[0]-3, (int)center[1]-3, 6, 6);

		g2.drawRect(10, 10, (int)dimensions[0]-20, (int)dimensions[1]-20);

		if (capture != null) {
			int distance = (int)GeomUtil.distance(capture, mouse);
			g2.drawString("" + distance, (int)capture[0]+5, (int)capture[1]+5);
			g2.fillOval((int)capture[0]-3, (int)capture[1]-3, 6, 6);
			g2.fillOval((int)mouse[0]-3, (int)mouse[1]-3, 6, 6);
			g2.drawLine((int)capture[0], (int)capture[1], (int)mouse[0], (int)mouse[1]);
		}
	}
}
