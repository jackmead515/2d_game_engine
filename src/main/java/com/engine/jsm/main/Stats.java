package com.engine.jsm.main;

public class Stats {
	
	private static float fps;
	private static boolean debug;
	private static int entityCount;
	private static int screenWidth;
	private static int screenHeight;
	private static double[] screenCenter;
	private static long gameTime;
	private static double delta;
	
	public static synchronized float getFps() {
		return fps;
	}
	public static synchronized void setFps(float fps) {
		Stats.fps = fps;
	}
	public static synchronized int getEntityCount() {
		return entityCount;
	}
	public static synchronized void setEntityCount(int entityCount) {
		Stats.entityCount = entityCount;
	}
	public static synchronized boolean isDebug() { return debug; }
	public static synchronized void setDebug(boolean debug) { Stats.debug = debug; }
	public static synchronized int getScreenWidth() {
		return screenWidth;
	}
	public static synchronized void setScreenWidth(int screenWidth) {
		Stats.screenWidth = screenWidth;
	}
	public static synchronized int getScreenHeight() {
		return screenHeight;
	}
	public static synchronized void setScreenHeight(int screenHeight) { Stats.screenHeight = screenHeight; }
	public static synchronized double[] getScreenCenter() { return screenCenter; }
	public static synchronized void setScreenCenter(double[] screenCenter) { Stats.screenCenter = screenCenter; }

	public static long getGameTime() {
		return gameTime;
	}
	public static void setGameTime(long gameTime) {
		Stats.gameTime = gameTime;
	}
	public static double getDelta() {
		return delta;
	}
	public static void setDelta(double delta) {
		Stats.delta = delta;
	}
}
