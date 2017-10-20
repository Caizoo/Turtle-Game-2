package gameState.play;

import gameState.GameState;
import gameState.GameStateManager;
import renderEngine.Loader;
import renderEngine.Renderer;

public abstract class PlayState extends GameState {

	public PlayState(GameStateManager gsm, Loader loader, Renderer renderer) {
		super(gsm, loader, renderer);
	}

}
