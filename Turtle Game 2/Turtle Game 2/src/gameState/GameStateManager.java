package gameState;

import gameState.play.PlayState;
import main.MainGameLoop;
import renderEngine.Loader;
import renderEngine.Renderer;

public class GameStateManager {

	private Loader loader;
	private Renderer renderer;
	
	private GameState[] gameStates;
	private int currentState = 0;
	
	public static final int NUMGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	
	public GameStateManager(Loader loader, Renderer renderer) {
		gameStates = new GameState[NUMGAMESTATES];
		this.loader = loader;
		this.renderer = renderer;
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	private void loadState(int state) {
		if(state==MENUSTATE) gameStates[state] = new MenuState(this,loader,renderer);
		if(state==PLAYSTATE) gameStates[state] = new PlayStateManager(this,loader,renderer);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
		System.gc();
	}
	
	public void setState(int state, boolean unloadState) {
		if(unloadState) unloadState(currentState);
		currentState = state;
		if(gameStates[currentState] == null) loadState(currentState);
	}
	
	public void update() {
		gameStates[currentState].update();
	}
	
	public void render() {
		gameStates[currentState].render();
	}
	
	public void exit() {
		MainGameLoop.running = false;
	}
	
}
