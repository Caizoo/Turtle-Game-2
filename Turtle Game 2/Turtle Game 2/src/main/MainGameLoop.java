package main;

import java.io.File;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import audio.AudioMaster;
import audio.Source;
import entities.Camera;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontMeshCreator.TextMeshCreator;
import fontRendering.Text;
import fontRendering.TextMaster;
import gameState.GameStateManager;
import gui.Button;
import gui.Component;
import gui.MenuGUI;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import toolbox.Maths;

public class MainGameLoop {
	
	public static boolean running = true;
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		AudioMaster.init();
		AudioMaster.setListenerData();
		Loader loader = new Loader();
		TextMaster.init(loader);
		Component.init();
		Renderer renderer = new Renderer(loader);
		GameStateManager gsm = new GameStateManager(loader, renderer);
		
		
		while(!Display.isCloseRequested() && running) {
	
			renderer.prepare();
			gsm.update();
			gsm.render();
			
			Component.renderAll(renderer);
			
			TextMaster.render();
			TextMaster.clear();
			
			DisplayManager.updateDisplay();
			// background updates
			Maths.update();
			Source.update();
			
		} // exit game loop
		
		renderer.cleanUp();
		TextMaster.cleanUp();
		if(!Source.sources.isEmpty()) {
			int i = 0;
			while(Source.isPlaying(Source.sources.get(0))) { // empty loop 
					i++;
					if(i<5000) break;
				}
			}
		DisplayManager.closeDisplay();
		
		
	} 
}

