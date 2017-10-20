package gameState;

import renderEngine.Loader;
import renderEngine.Renderer;

public abstract class GameState {

	public GameStateManager gsm;
	protected Loader loader;
	protected Renderer renderer;
	
	public GameState(GameStateManager gsm, Loader loader, Renderer renderer) {
		this.gsm = gsm;
		this.loader = loader;
		this.renderer = renderer;
		init();
	}
	
	public abstract void init();
	public abstract void loadTextures(Loader loader);
	public abstract void update();
	public abstract void render();

	
	
}
