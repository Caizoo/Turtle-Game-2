package toolbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import renderEngine.DisplayManager;

public class Maths {
	
	private static ArrayList<Integer> keysHeld = new ArrayList<Integer>();
	private static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	
	private static boolean mouseHeld = false;
	private static int loopsReleased = 0;
	private static int loopsHeld = 0;
	
	// constants
	public static final Vector2f gravity = new Vector2f(0,9.8f/4f);
	public static final float groundLevel = 2.9f; // in m
	public static final float maxCloudHeight = -750f;
	public static final float minCloudHeight = 0.5f;
	public static final float pixelToWorldScale = 200;
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, float rotation) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1.f), matrix, matrix);
		Matrix4f.rotate(rotation, new Vector3f(0,0,1), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformation3dMatrix(Vector2f translation, Vector2f scale, float rotationZ, float rotationY, float rotationX) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1.f), matrix, matrix);
		Matrix4f.rotate(rotationZ, new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.rotate(rotationY, new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate(rotationX, new Vector3f(1,0,0), matrix, matrix);
		return matrix;
	}

	public static Matrix4f createTransformation3dMatrix(Vector3f translation, Vector2f scale, float rotationZ, float rotationY, float rotationX) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1.f), matrix, matrix);
		Matrix4f.rotate(rotationZ, new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.rotate(rotationY, new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate(rotationX, new Vector3f(1,0,0), matrix, matrix);
		return matrix;
	}
	
	public static Vector2f vectorAddition(Vector2f v1, Vector2f v2) {
		float x = v1.x + v2.x;
		float y = v1.y + v2.y;
		return new Vector2f(x,y);
	}
	
	public static Vector2f vectorSubtraction(Vector2f v1, Vector2f v2) {
		float x = v1.x - v2.x;
		float y = v1.y - v2.y;
		return new Vector2f(x,y);
	}
	
	public static Vector2f vectorScale(Vector2f v1, float multiplier) {
		return new Vector2f(v1.x*multiplier, v1.y*multiplier);
	}
	
	public static float pixelsToWorld(float pixels) {
		return pixels/pixelToWorldScale;
	}
	
	public static float worldToPixels(float meters) {
		return meters*pixelToWorldScale;
	}
	
	public static Vector2f worldToPixels(Vector2f worldPosition) {
		float x = worldPosition.x*pixelToWorldScale;
		float y = worldPosition.y*pixelToWorldScale;
		return new Vector2f(x,y);
	}
	
	public static float getHeight(float realPositionY, float realSizeY) {
		realPositionY -= groundLevel;
		return Math.abs(realPositionY)-realSizeY/2;
	}
	
	public static Vector2f dragForce(Vector2f velocity, float dragCoefficient) {
		int xSign = 1;
		int ySign = 1;
		if(velocity.x>0) xSign = -1;
		if(velocity.y>0) ySign = -1;
		float p = 1.225f; // at sea level
		float forceX = xSign * (float) (0.5*p*dragCoefficient*Math.pow(velocity.x, 2)*1.035);
		float forceY = ySign * (float) (0.5*p*dragCoefficient*Math.pow(velocity.y, 2)*1.035);
		return new Vector2f(forceX, forceY); // scaling force back to be an realistic size
	}
	
	public static Vector2f frictionForce(Vector2f velocity) {
		float forceX = (float) (Math.pow(velocity.x, 2)*3);
		if(velocity.x>0) forceX *=1;
		return new Vector2f(forceX, 0);
	}
	
	public static boolean pointIntersectsBox(Vector2f point, Vector2f position, Vector2f size) {
		if(point.x > position.x - (size.x/2) && point.x < position.x + (size.x/2)) {
			if(point.y > position.y - (size.y/2) && point.y < position.y + (size.y/2)) {
				return true;
			}
		}
		return false;
	}
	
	public static float clamp(float value, float min, float max) {
		if(value > max) value = max;
		if(value < min) value = min;
		return value;
	}
	
	public static void update() {
		keysPressed.clear();
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				keysHeld.add(Keyboard.getEventKey());
				keysPressed.add(Keyboard.getEventKey());
			}else{
				Iterator<Integer> iterator = keysHeld.iterator();
				while(iterator.hasNext()) {
					if(iterator.next()==Keyboard.getEventKey()) {
						iterator.remove();
					}
				}
			}
		}
		
		if(!Mouse.isButtonDown(0) && mouseHeld) {
			if(loopsReleased >= 20) {
				mouseHeld = false;
				loopsReleased = 0;
			}else{
				loopsReleased ++;
			}
		}
		
	}
	
	public static boolean isKeyHeld(int key) {
		Iterator<Integer> iterator = keysHeld.iterator();
		while(iterator.hasNext()) if(iterator.next()==key) return true;
		return false;
	}
	
	public static boolean isKeyPressed(int key) {
		Iterator<Integer> iterator = keysPressed.iterator();
		while(iterator.hasNext()) if(iterator.next()==key) return true;
		return false;
	}
	
	public static boolean isMousePressed() {
		if(Mouse.isButtonDown(0) && !mouseHeld) {
			mouseHeld = true;
			return true;
		}
		return false;
	}
	
	public static boolean isMouseHeld() { return mouseHeld; }
	public static boolean isMouseClicked() { 
		if(mouseHeld && !Mouse.isButtonDown(0)) return true;
		return false;
	}
	
	public static Vector2f getMouseScreenCoords() {
		Vector2f screenCoords = new Vector2f(Mouse.getX(), Mouse.getY());
		screenCoords.y -= DisplayManager.VIRTUAL_HEIGHT;
		screenCoords.y *= -1;
		return screenCoords;
	}
	
	public static String[] readFile(String file) {
		ArrayList<String> lines = new ArrayList<String>();
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line = "";
			while((line=buffer.readLine())!=null) {
				lines.add(line);
			}
			buffer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		String[] strLines = new String[lines.size()];
		for(int i = 0;i<strLines.length;i++) {
			strLines[i] = lines.get(i);
		}
		return strLines;
	}
	
	public static Vector3f rgbToGL(Vector3f colour) {
		float r = colour.x / 255f;
		float g = colour.y / 255f;
		float b = colour.z / 255f;
		return new Vector3f(r,g,b);
	}
	
	public static float linearInterpolate(float a, float b, float factor) { return a * (1f - factor) + b * factor; }

}
