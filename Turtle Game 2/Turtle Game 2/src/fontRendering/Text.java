package fontRendering;

import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import toolbox.Maths;

public class Text {
	
	Loader loader;
	
	protected String textString;
	protected float fontSize;
	protected FontType font;
	protected Vector3f colour;
	protected Vector2f position;
	protected float lineMaxSize;
	protected boolean centerText;
	protected float width;
	protected float height;
	protected float paddedHeight;
	
	protected float effectWidth = 0.5f;
	protected float edge = 0.12f;
	protected float borderWidth = 0.0f;
	protected float borderEdge = 0.12f;
	protected Vector2f offset = new Vector2f(0,0);
	protected Vector3f borderColour = new Vector3f(0,0,0);
	
	protected float rotationY = 0;
	protected float rotationX = 0;

	public Text(String text, Loader loader, float fontsize, FontType font,
			Vector2f position, float maxLineLength, boolean centered) {
		this.textString = text;
		this.loader = loader;
		this.fontSize = fontsize;
		this.font = font;
		this.position = position;
		this.lineMaxSize = maxLineLength;
		this.centerText = centered;
		this.width = getWidth(fontsize, textString, font.fontName)*(float)(DisplayManager.VIRTUAL_HEIGHT);
		this.height = getHeight(fontsize)*(float)(DisplayManager.VIRTUAL_HEIGHT);
		this.paddedHeight = getPaddedHeight(fontsize)*(float)(DisplayManager.VIRTUAL_WIDTH);
		this.colour = new Vector3f(0,0,0);
		this.effectWidth = 0.5f;
		this.edge = 0.12f;
		this.borderWidth = 0.0f;
		this.borderEdge = 0.12f;
	}
	
	public Text(String text, Loader loader, float fontsize, String font, Vector2f screenPosition) {
		this.textString = text;
		this.loader = loader;
		this.fontSize = fontsize;
		this.font = TextMaster.getFont(font);
		this.position = mouseToTextCoords(screenPosition,fontsize,text,font);
		this.lineMaxSize = 1;
		this.centerText = true;
		this.width = getWidth(fontsize, textString, font)*(float)(DisplayManager.VIRTUAL_HEIGHT);
		this.height = getHeight(fontsize)*(float)(DisplayManager.VIRTUAL_HEIGHT);
		this.paddedHeight = getPaddedHeight(fontsize)*(float)(DisplayManager.VIRTUAL_WIDTH);
		this.colour = new Vector3f(0,0,0);
		this.effectWidth = 0.5f;
		this.edge = 0.12f;
		this.borderWidth = 0.0f;
		this.borderEdge = 0.12f;
	}
	
	public Vector2f getPosition() { return position; }
	
	public void updatePosition(Vector2f position) {
		this.position = position;
	}
	
	public static Vector2f mouseToTextCoords(Vector2f position, float fontSize, String text, String font) {
		float mouseX = position.x;
		float mouseY = position.y;
		mouseX /= (float)DisplayManager.VIRTUAL_WIDTH;
		mouseY /= (float)DisplayManager.VIRTUAL_HEIGHT;
		mouseX -= 0.5f;
		mouseX -= getWidth(fontSize,text,font)*0.025*text.length();
		mouseY -= getHeight(fontSize);
		return new Vector2f(mouseX,mouseY);
	}
	
	public static Vector2f textToMouseCoords(Vector2f position, float fontSize) {
		float x = position.getX();
		float y = position.getY();
		y += getHeight(fontSize);
		x += 0.5f;
		y *= (float)(DisplayManager.VIRTUAL_HEIGHT);
		x *= (float)(DisplayManager.VIRTUAL_WIDTH);
		return new Vector2f(x,y);
	}
	
	public static float getWidth(float fontSize, String string, String font) {
		String[] str = string.split("");
		float sizeX = 0;
		for(String s:str) {
			sizeX += TextMaster.getFont(font).characterSizes.get(s)*fontSize;
		}
		return sizeX;
	}
	
	public static float getSizeForWidth(float width, int stringSize) {
		return width/(stringSize*11);
	}

	public static float getHeight(float fontSize) {
		return (fontSize*11)/(float)(DisplayManager.VIRTUAL_HEIGHT);
	}
	
	public static float getSizeForHeight(float height) {
		return height/13;
	}
	
	public static float getPaddedHeight(float fontSize) {
		return (fontSize*13)/(float)(DisplayManager.VIRTUAL_HEIGHT);
	}
	
	public static float getPaddedWidth(float fontSize, int stringSize) {
		return (fontSize*12*stringSize)/DisplayManager.VIRTUAL_WIDTH;
	}
	
	public static float getPixelWidth(float fontSize, int stringSize) {
		return fontSize*9*stringSize;
	}
	
	public static float getPixelHeight(float fontSize) {
		return fontSize*11;
	}
	
	public static float getPaddedPixelWidth(float fontSize, int stringSize) {
		return fontSize*11*stringSize;
	}
	
	public static float getPaddedPixelHeight(float fontSize) {
		return fontSize*13;
	}
	
	public void setColour(Vector3f colour) { this.colour = colour; }
	
	public void render(Renderer renderer) {
		Vector2f offPos = new Vector2f(position.x + 0.5f - (width/4/DisplayManager.VIRTUAL_HEIGHT),position.y);
		lineMaxSize = width/DisplayManager.VIRTUAL_HEIGHT;
		GUIText g = new GUIText(textString,fontSize,font,offPos,lineMaxSize,false);
		g.setWidth(effectWidth);
		g.setEdge(edge);
		g.setBorderWidth(borderWidth);
		g.setBorderEdge(borderEdge);
		g.setOffset(offset);
		g.setBorderColour(borderColour);
		g.setColour(colour.x, colour.y, colour.z);
		g.setRotationY(rotationY);
		g.setRotationX(rotationX);
		TextMaster.loadText(g);
	}
	
	public float getEffectWidth() { return effectWidth; }
	public float getEdge() { return edge; }
	public float getBorderWidth() { return borderWidth; }
	public float getBorderEdge() { return borderEdge; }
	public Vector2f getOffset() { return offset; }
	public Vector3f getBorderColour() { return borderColour; }
	public float getRotationY() { return rotationY; }
	public float getRotationX() { return rotationX; }
	public float getFontSize() { return fontSize; }
	
	public void setEffectWidth(float width) { this.effectWidth = width; }
	public void setEdge(float edge) { this.edge = edge; }
	public void setBorderWidth(float width) { borderWidth = width; }
	public void setBorderEdge(float edge) { borderEdge = edge; }
	public void setOffset(Vector2f offset) { this.offset = offset; }
	public void setBorderColour(Vector3f colour) { this.borderColour = colour; }
	public void setRotationY(float rotation) { this.rotationY = rotation; }
	public void setRotationX(float rotation) { this.rotationX = rotation; }
	
}
