package gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import fontRendering.Text;
import fontRendering.TextMaster;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.textures.Texture;
import toolbox.Maths;

public abstract class MenuGUI extends Component {
	
	ArrayList<Texture> textures;
	Texture texture;
	Texture hoverTexture;
	boolean hovered;
	Text text;

	@Override
	public void render(Renderer renderer) {
		if(textures!=null) {
			renderer.render(textures);
		}else if(texture!=null) {
			texture.updateTexture(Maths.vectorAddition(screenPosition, positionOffset), Maths.vectorScale(screenSize, scale));
			hoverTexture.updateTexture(Maths.vectorAddition(screenPosition, positionOffset), Maths.vectorScale(screenSize, scale));
			if(!hovered) {
				renderer.render(texture);
			}else{
				renderer.render(hoverTexture);
			}
		}
		if(text!=null) {
			text.render(renderer);
		}
	}

}
