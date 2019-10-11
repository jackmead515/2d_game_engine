package com.engine.jsm.display;

import com.engine.jsm.input.InputManager;
import com.engine.jsm.main.Constants;
import com.engine.jsm.main.Stats;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

	public GraphicsDevice graphicsDevice;
	public GameCanvas gameCanvas;

	public Display() {
		super();
		graphicsDevice = this.getDisplay();
		gameCanvas = new GameCanvas();
	}
	
	public GraphicsDevice getDisplay() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		return gs[0];
	}

	public void paint() {
		this.gameCanvas.paint();
	}

	public void init() {
		GraphicsConfiguration gconfig = graphicsDevice.getDefaultConfiguration();
		Stats.setScreenWidth(gconfig.getBounds().width);
		Stats.setScreenHeight(gconfig.getBounds().height);
		Stats.setScreenCenter(new double[] { Stats.getScreenWidth()/2, Stats.getScreenHeight()/2 });

		gameCanvas.setBounds(0, 0, Stats.getScreenWidth(), Stats.getScreenHeight());
		gameCanvas.addMouseListener(InputManager.getMouse());
		gameCanvas.addMouseMotionListener(InputManager.getMouse());
		gameCanvas.addKeyListener(InputManager.getKeyboard());

		this.graphicsDevice.setFullScreenWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.add(gameCanvas);
		this.pack();

		gameCanvas.createBufferStrategy(2);
	}

	public void destroy() {

	}

}
