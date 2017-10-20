package gui;

import org.lwjgl.util.vector.Vector2f;

import toolbox.Maths;

public class ZoomButton extends Button{
	
	float scaleFactor = 1;

	public ZoomButton(Vector2f position, Vector2f size, String buttonFile, String hoverFile, String text, float scaleFactor) {
		super(position, size, buttonFile, hoverFile, text);
		this.scaleFactor = scaleFactor;
	}
	
	@Override 
	public void hovered() {
		super.hovered();
		super.scale(scaleFactor);
		super.changeTextSize(scaleFactor);
	}
	
	@Override
	public void checkHover() {
		if(Maths.pointIntersectsBox(Maths.getMouseScreenCoords(), screenPosition, screenSize)) {
			hovered();
		}else{
			super.scale(1);
			super.changeTextSize(1);
		}
	}

}
