package gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.textures.Texture;
import toolbox.Maths;

public class ScrollBar extends MenuGUI {
	
	ArrayList<MenuGUI> components;
	Vector2f scrollOffset;
	Vector2f scrollSize;
	
	boolean vertical;
	
	public ScrollBar(ArrayList<MenuGUI> c, Vector2f position, Vector2f size, boolean vertical) {
		this.components = c;
		this.screenPosition = position;
		this.screenSize = size;
		this.vertical = vertical;
	}
	
	@Override
	public void init(Loader loader) {
		loadTextures(loader);
	}

	@Override
	public void loadTextures(Loader loader) {
		textures = new ArrayList<Texture>();
		if(vertical) {
			textures.add(new Texture("UI/scrollBarBack",loader,screenPosition,screenSize,0.1f));
		}else{
			textures.add(new Texture("UI/scrollBarBack",loader,screenPosition,screenSize));
		}
	}

	@Override
	public void update() {
		Texture t = textures.get(0);
		t.updateTexture(t.getPosition(), t.getSize(), t.getRotation()+0.001f);
		if(Maths.pointIntersectsBox(Maths.getMouseScreenCoords(), textures.get(0).getPosition(), textures.get(0).getSize())) {
			if(Maths.isMouseHeld()) {
				
			}
		}
	}
	
	

}
