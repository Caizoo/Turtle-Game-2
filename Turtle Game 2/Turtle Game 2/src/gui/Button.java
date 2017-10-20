package gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import fontRendering.Text;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.textures.Texture;
import toolbox.Maths;

public class Button extends MenuGUI implements Clickable {
	
	String buttonFileName;
	String buttonHoverFileName;
	String strText;
	Vector2f textPosition;
	
	boolean clicked;
	Loader loader;
	
	public Button(Vector2f position, Vector2f size, String buttonFile, String hoverFile, String text) {
		this.screenPosition = position;
		this.screenSize = size;
		this.buttonFileName = buttonFile;
		this.buttonHoverFileName = hoverFile;
		this.strText = text;
	}
	
	@Override
	public void init(Loader loader) {
		this.loader = loader;
		loadTextures(loader);
	}

	@Override
	public void render(Renderer renderer) {
		super.render(renderer);
	}
	
	@Override
	public void loadTextures(Loader loader) {
		texture = new Texture(buttonFileName,loader,screenPosition,screenSize);
		hoverTexture = new Texture(buttonHoverFileName,loader,screenPosition,screenSize);
		if(strText!="") {
			float textSize = Text.getSizeForWidth(screenSize.x*0.9f, strText.length());
			
			if(Text.getPaddedHeight(textSize)*DisplayManager.VIRTUAL_HEIGHT>screenSize.y*0.9) {
				textSize = Text.getSizeForHeight(screenSize.y*0.9f);
			}

			text = new Text(strText, loader, textSize, "Cambria", screenPosition);
		}
	}

	@Override
	public void update() {
		hovered = false;
		checkHover();
		checkClicked();
	}

	@Override
	public void checkHover() {
		if(Maths.pointIntersectsBox(Maths.getMouseScreenCoords(), screenPosition, screenSize)) {
			hovered();
		}
	}

	@Override
	public void checkClicked() {
		if(hovered && Maths.isMousePressed()) {
			clicked();
		}
	}

	@Override
	public void hovered() {
		hovered = true;
	}

	@Override
	public void clicked() {
		clicked = true;
	}
	
	public void changeTextPosition(Vector2f position, Loader loader) {
		this.textPosition = position;
		if(text!=null) text = new Text(strText, loader, text.getFontSize(), "Cambria", textPosition);
	}
	
	public void changeTextSize(float scale) {
		float textSize = Text.getSizeForWidth(screenSize.x*(scale-0.1f), strText.length());
		
		if(Text.getPaddedHeight(textSize)*DisplayManager.VIRTUAL_HEIGHT>screenSize.y*(scale-0.1f)) {
			textSize = Text.getSizeForHeight(screenSize.y*(scale-0.1f));
		}

		text = new Text(strText, loader, textSize, "Cambria", screenPosition);
	}

}
