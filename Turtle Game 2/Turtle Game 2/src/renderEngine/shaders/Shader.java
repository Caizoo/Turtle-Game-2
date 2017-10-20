package renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class Shader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "/renderEngine/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "/renderEngine/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_rotation;
	private int location_transparency;
	private int location_brightness;
	private int location_grayscale;
	
	public Shader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	public void loadTransformation(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
		super.loadFloat(location_transparency, 1);
		super.loadFloat(location_brightness, 1);
		super.loadBoolean(location_grayscale, false);
	}
	
	public void loadRotation(float rotation) { super.loadFloat(location_rotation, rotation); }
	public void loadTransparency(float transparency) { super.loadFloat(location_transparency, transparency); }
	public void loadBrightness(float brightness) { super.loadFloat(location_brightness, brightness); }
	public void loadGrayscale() { super.loadBoolean(location_grayscale, true); }
	
	public void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_rotation = super.getUniformLocation("rotation");
		location_transparency = super.getUniformLocation("transparency");
		location_brightness = super.getUniformLocation("brightness");
		location_grayscale = super.getUniformLocation("grayscale");
	}

	protected void bindAttributes() { super.bindAttribute(0, "position"); }
	
}
