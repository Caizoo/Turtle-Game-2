package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static final int VIRTUAL_WIDTH = 1280;
	public static final int VIRTUAL_HEIGHT = 720;
	
	private static final String TITLE = "Turtles can fly";
	public static final int FPS_CAP = 120;
	
	private static long lastFrameTime;
	private static double delta;
	
	private static float slow_down_factor = 1f;
	
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3,3)
		.withForwardCompatible(true)
		.withProfileCore(true);
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setTitle(TITLE);
			Display.setFullscreen(false);
			Display.create(new PixelFormat(), attribs);
		}catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime);
		delta /= 1000;
		delta *= slow_down_factor;
		lastFrameTime = currentFrameTime;
	}
	
	public static float getFrameTimeSeconds() { return (float)delta; }
	public static void closeDisplay() { Display.destroy(); }
	private static long getCurrentTime() { return Sys.getTime() * 1000 / Sys.getTimerResolution(); }
	
}
