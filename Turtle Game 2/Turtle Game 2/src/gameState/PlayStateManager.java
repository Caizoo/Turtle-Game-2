package gameState;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import entities.Camera;
import gameState.play.PlayState;
import renderEngine.Loader;
import renderEngine.Renderer;
import toolbox.Maths;

public class PlayStateManager extends GameState {
	
	PlayState[] playStates;
	public static final int NUMPLAYSTATES = 2;
	private int currentState;
	
	public PlayStateManager(GameStateManager gsm, Loader loader, Renderer renderer) {
		super(gsm, loader, renderer);
	}

	public void init() {
		playStates = new PlayState[NUMPLAYSTATES];
		currentState = 1;
		loadState(currentState);
		loadTextures(loader);
	}

	public void loadTextures(Loader loader) {
		
	}

	public void update() {
		playStates[currentState].update();
	}

	public void render() {
		playStates[currentState].render();
	}
	
	private void loadState(int state) {

	}
	
	private void unloadState(int state) {
		playStates[state] = null;
	}
	
	public void setState(int state, boolean unload) {
		if(unload) unloadState(state);
		currentState = state;
		loadState(state);
	}

}
