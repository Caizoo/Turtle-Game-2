package gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;
import renderEngine.Renderer;
import toolbox.Maths;

public abstract class Component {

	protected Vector2f screenPosition;
	protected Vector2f screenSize;
	protected Vector2f positionOffset;
	protected float scale;
	
	private static ArrayList<Component> components;
	
	public static void init() {
		components = new ArrayList<Component>();
	}
	
	public Component() {
		components.add(this);
		positionOffset = new Vector2f(0,0);
		scale = 1f;
	}
	
	public static void renderAll(Renderer renderer) {
		for(Component c:components) {
			c.render(renderer);
		}
	}
	
	public void scale(float scale) {
		this.scale = scale;
	}
	
	public void move(Vector2f moveBy) {
		Maths.vectorAddition(positionOffset, moveBy);
	}
	
	public void revertOffseting() {
		scale = 1;
		positionOffset = new Vector2f(0,0);
	}
	
	public abstract void render(Renderer renderer);
	public abstract void init(Loader loader);
	public abstract void loadTextures(Loader loader);
	public abstract void update();
	
}
