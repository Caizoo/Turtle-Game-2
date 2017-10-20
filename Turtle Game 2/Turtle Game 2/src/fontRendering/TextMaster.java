package fontRendering;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontMeshCreator.TextMeshData;
import renderEngine.Loader;

public class TextMaster {
	
	private static Loader loader;
	private static HashMap<FontType, ArrayList<GUIText>> texts = new HashMap<FontType, ArrayList<GUIText>>();
	private static FontRenderer renderer;
	
	private static HashMap<String, FontType> fonts = new HashMap<String, FontType>();
	
	public static void init(Loader loader) {
		renderer = new FontRenderer();
		TextMaster.loader = loader;
		fonts.put("Cambria", new FontType(loader.loadFontTexture("fonts/Cambria"),new File("res/fonts/Cambria.fnt")));
	}
	
	public static void render() {
		renderer.render(texts);
	}
	
	public static void loadText(GUIText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		ArrayList<GUIText> textBatch = texts.get(font);
		if(textBatch==null) {
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public static void removeText(GUIText text) {
		ArrayList<GUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty()) {
			texts.remove(text.getFont());
			GL30.glDeleteVertexArrays(text.getMesh());
		}
	}
	
	public static void clear() {
		texts.clear();
	}
	
	public static void cleanUp() {
		renderer.cleanUp();
	}
	
	public static FontType getFont(String font) {
		return fonts.get(font);
	}

}
