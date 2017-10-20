package gameState;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import fontRendering.Text;
import gui.Button;
import gui.MenuGUI;
import gui.ScrollBar;
import gui.ZoomButton;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.textures.Texture;
import toolbox.Maths;

public class MenuState extends GameState {
	
	Texture background;
	ZoomButton b;
	ScrollBar s;
	
	public MenuState(GameStateManager gsm, Loader loader, Renderer renderer) {
		super(gsm,loader,renderer);
	}
	
	@Override
	public void init() {
		b = new ZoomButton(new Vector2f(640,360),new Vector2f(200,200),"UI/dark_grey_box","UI/dark_grey_box_hover","Play",1.2f);
		ArrayList<MenuGUI> guis = new ArrayList<MenuGUI>();
		guis.add(b);
		s = new ScrollBar(guis,new Vector2f(640,360),new Vector2f(300,100),true);
		loadTextures(loader);
	}

	@Override
	public void loadTextures(Loader loader) {
		background = new Texture("UI/grid",loader,new Vector2f(640,360),new Vector2f(1280,720));
		//b.init(loader);
		s.init(loader);
		b.changeTextPosition(new Vector2f(640,360), loader);
	}
	
	@Override
	public void update() {
		//b.update();
		s.update();
	}

	@Override
	public void render() {
		renderer.render(background);
	}

}
