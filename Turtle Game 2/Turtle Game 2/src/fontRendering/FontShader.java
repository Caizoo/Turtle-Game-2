package fontRendering;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.shaders.ShaderProgram;

public class FontShader extends ShaderProgram{

	private static final String VERTEX_FILE = "/fontRendering/fontVertex.txt";
	private static final String FRAGMENT_FILE = "/fontRendering/fontFragment.txt";
	
	private int location_colour;
	private int location_translation;
	private int location_matrix;
	
	private int location_width;
	private int location_edge;
	private int location_borderWidth;
	private int location_borderEdge;
	private int location_offset;
	private int location_borderColour;
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_colour = super.getUniformLocation("colour");
		location_translation = super.getUniformLocation("translation");
		location_matrix = super.getUniformLocation("matrix");
		location_width = super.getUniformLocation("width");
		location_edge = super.getUniformLocation("edge");
		location_borderWidth = super.getUniformLocation("borderWidth");
		location_borderEdge = super.getUniformLocation("borderEdge");
		location_offset = super.getUniformLocation("offset");
		location_borderColour = super.getUniformLocation("borderColour");
	}
	
	protected void loadColour(Vector3f colour) {
		super.loadVector(location_colour, colour);
		super.loadVector(location_borderColour, colour);
	}
	
	protected void loadTranslation(Vector2f translation) {
		super.loadVector2D(location_translation, translation);
	}
	
	protected void loadMatrix(Matrix4f matrix) {
		super.loadMatrix(location_matrix, matrix);
	}
	
	protected void loadWidth(float width) { super.loadFloat(location_width, width); }
	protected void loadEdge(float edge) { super.loadFloat(location_edge, edge); }
	protected void loadBorderWidth(float width) { super.loadFloat(location_borderWidth, width); }
	protected void loadBorderEdge(float edge) { super.loadFloat(location_borderEdge, edge); }
	protected void loadOffset(Vector2f offset) { super.loadVector2D(location_offset, offset); }
	protected void loadBorderColour(Vector3f colour) { super.loadVector(location_borderColour, colour); }

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}


}
