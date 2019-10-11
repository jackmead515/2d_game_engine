package com.engine.jsm.engine;

import com.engine.jsm.main.Constants;
import com.engine.jsm.main.Main;
import com.engine.jsm.main.Stats;

public class EngineManager {

	private boolean pause;
	
	public void start() {
		this.pause = false;
	}

	public void pause() {
		this.pause = true;
	}

	/**
	 * Starts the loop which runs the update / render cycle.
	 */
	public void loop() {
		final long OPTIMAL_TIME = 1000000000L / Constants.TARGET_FPS;
		long lastLoopTime = System.nanoTime();
		int lastFpsTime = 0;
		int fps = 0;

		while (!this.pause) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			double delta = updateLength / (double)OPTIMAL_TIME;
			lastLoopTime = now;
			lastFpsTime += updateLength;
			fps+=1;

			Stats.setGameTime(now);
			Stats.setDelta(delta);
			if (lastFpsTime >= 1000000000) {
				Stats.setFps(fps);
				lastFpsTime = 0;
				fps = 0;
			}

			this.update();
			this.render();

			try {
				Thread.sleep(Math.abs((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000));
			} catch(Exception e) {};
		}
	}

	public void update() {
		Main.world.update();
	}

	public void render() {
		Main.display.paint();
	}

	public void close() {
		
	}

}
