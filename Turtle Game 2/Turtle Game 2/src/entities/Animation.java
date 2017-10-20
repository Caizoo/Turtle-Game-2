package entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.textures.Texture;
import toolbox.Maths;

public class Animation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Texture[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation() {
		playedOnce = false;
	}
	
	public void setFrames(Texture[] frames) {
		this.frames = frames;
		currentFrame = 0;
		playedOnce = false;
	}
	
	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { currentFrame = i; }
	
	public void update() {
		
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}
	}
	
	public int getFrame() { return currentFrame; }
	public Texture getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }
	public void stopAnimation() { 
		currentFrame = frames.length-1;
	}
	public void updateTextures(Vector2f worldPosition,Vector2f realSize) {
		for(int i = 0;i<frames.length;i++) {
			frames[i].updateTexture(Camera.getScreenPosition(worldPosition), Maths.worldToPixels(realSize));
		}
	}

}
