package com.bipolar.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class Pad extends Entity{
	
	private boolean activated;
	
	public Pad(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("pad"), 84, 16);
		this.state = state;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
		this.activated = false;
	}

	
	public void activate() {
		this.setState(0);
		this.image = this.sheet.getSubImage(1, 0);
	}
	
	public void update() {
		if (this.state == 1 && !this.activated) {
			this.activate();
			this.activated = true;
		}
	}
	
	public void render(Camera c) {
		transform(c);
		new Transform();
		if (image != null) {
			image.draw(transformedPosition.x, transformedPosition.y);
		}
		Transform drawTf = Transform.createTranslateTransform
				(this.transformedPosition.x - this.position.x , this.transformedPosition.y - this.position.y);
		Level.drawObj.setColor(Color.black);
	}
	
}
