package renderEngine.textures;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;
import renderEngine.Loader;

public class Texture {

	private int texture;
	private Vector2f screenPosition;
	private Vector2f screenScale;
	private TextureData data;
	private Vector2f size;
	private Vector2f position;
	private float rotation = 0;
	
	private float transparency = 1;
	
	public Texture(String fileName, Loader loader, Vector2f position, Vector2f size) {
		super();
		this.texture = loader.loadTexture(fileName);
		data = Loader.decodeTextureFile(fileName);
		makeScreenPosition(position);
		makeScreenScaleVector(size);
		this.position = position;
		this.size = size;
	}
	
	public Texture(int id, Vector2f position, Vector2f size) { // when making a texture object when a texture is already loaded i.e. for fbos
		super();
		this.texture = id;
		this.position = position;
		this.size = size;
		this.screenPosition = position;
		this.screenScale = size;
	}
	
	public Texture(String fileName, Loader loader, Vector2f position, Vector2f size, float rotation) {
		super();
		this.texture = loader.loadTexture(fileName);
		data = Loader.decodeTextureFile(fileName);
		makeScreenPosition(position);
		makeScreenScaleVector(size);
		this.position = position;
		this.size = size;
		this.rotation = rotation;
	}
	
	private void makeScreenPosition(Vector2f position) {
		float x = (position.getX()/DisplayManager.VIRTUAL_WIDTH) * 2f - 1f;
		float y = (position.getY()/DisplayManager.VIRTUAL_HEIGHT) * 2f - 1f;
		y *= -1;
		this.screenPosition = new Vector2f(x,y);
	}
	
	private void makeScreenScaleVector(Vector2f size) {
		float width = (size.getX() / (float)(DisplayManager.VIRTUAL_WIDTH));
		float height = (size.getY() / (float)(DisplayManager.VIRTUAL_HEIGHT));
		this.screenScale = new Vector2f(width, height);
	}
	
	public void updateTexture(Vector2f position, Vector2f size) {
		makeScreenPosition(position);
		makeScreenScaleVector(size);
		this.position = position;
		this.size = size;
	}
	
	public void updateTexture(Vector2f position, Vector2f size, float rotation) {
		makeScreenPosition(position);
		makeScreenScaleVector(size);
		this.position = position;
		this.size = size;
		this.rotation = rotation;
	}
	
	public void setTextureId(int id) { this.texture = id; }
	public void setRotation(float rotation) { this.rotation = rotation; }
	public void setTransparency(float transparency) { this.transparency = transparency; }
	public int getTexture() { return texture; }
	public Vector2f getScreenPosition() { return screenPosition; }
	public Vector2f getScreenScale() { return screenScale; }
	public Vector2f getPosition() { return position; }
	public Vector2f getSize() { return size; }
	public float getRotation() { return rotation; }
	public float getTransparency() { return transparency; }
	
	
}
