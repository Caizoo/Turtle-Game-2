package renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class PartialShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "/renderEngine/shaders/partialVertex.txt";
	private static final String FRAGMENT_FILE = "/renderEngine/shaders/partialFragment.txt";
	
	private int location_transformationMatrix;
	private int location_rotation;
	private int location_widthFract;
	private int location_heightFract;
	private int location_transparency;
	private int location_brightness;
	private int location_grayscale;
	
	public PartialShader() {
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
	public void loadFractions(float width, float height) {
		super.loadFloat(location_widthFract, width);
		super.loadFloat(location_heightFract, height);
	}
	public void loadBrightness(float brightness) { super.loadFloat(location_brightness, brightness); }
	public void loadGrayscale() { super.loadBoolean(location_grayscale, true); }
	
	public void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_rotation = super.getUniformLocation("rotation");
		location_widthFract = super.getUniformLocation("widthFract");
		location_heightFract = super.getUniformLocation("heightFract");
		location_transparency = super.getUniformLocation("transparency");
		location_brightness = super.getUniformLocation("brightness");
		location_grayscale = super.getUniformLocation("grayscale");
	}

	protected void bindAttributes() { super.bindAttribute(0, "position"); }
	
}
