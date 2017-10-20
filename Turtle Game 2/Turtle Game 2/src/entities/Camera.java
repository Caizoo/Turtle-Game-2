package entities;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;
import toolbox.Maths;

public class Camera {
	
	public static float xOffset = 0;
	public static float yOffset = 0;
	public static float xDistance = 1280;
	public static float yDistance = 720;
	
	public static boolean isOnCamera(Vector2f position, Vector2f size) {
		if(position.x > xOffset - size.x/2 && position.x < (xOffset + xDistance) + size.x/2) {
			if(position.y > yOffset - size.y/2 && position.y < (yOffset + yDistance) + size.y/2) {
				return true;
			}
		}
		return false;
	}
	
	public static void update(Vector2f position) {
		xOffset = position.x;
		yOffset = position.y;
	}
	
	public static Vector2f getWorldOffset() { return new Vector2f(xOffset, yOffset); }
	public static Vector2f getScreenPosition(Vector2f worldPosition) {
		Vector2f position = Maths.vectorSubtraction(worldPosition, new Vector2f(xOffset,yOffset));
		position = Maths.vectorScale(position, Maths.pixelToWorldScale);
		position.x += DisplayManager.VIRTUAL_WIDTH/2;
		position.y += DisplayManager.VIRTUAL_HEIGHT/2;
		return position;
	}

}
