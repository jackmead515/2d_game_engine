package com.engine.jsm.display;

import com.engine.jsm.main.Main;
import com.engine.jsm.util.FloatRect;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	public GamePanel() {
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.setIgnoreRepaint(true);
	}
	
	public void draw() {
		FloatRect viewPoint = Main.world.getCamera().getViewPort();
		this.setLocation((int)-viewPoint.x(), (int)-viewPoint.y());
		this.repaint((int)viewPoint.x(), (int)viewPoint.y(), (int)viewPoint.width(), (int)viewPoint.height());
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		Main.world.render(g2, this);

		g2.dispose();
	}
}
