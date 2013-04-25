package com.bipolar.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class Pad extends Entity{

	public Pad(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("pad");
		this.hitbox = new Rectangle(this.position.x, this.position.y,
				this.image.getWidth(), this.image.getHeight());
	}

	
	public void activate() {
		System.out.println("activated!!!!!");
		this.setState(0);
	}
	
	public void update() {
		if (this.state == 1) {
			this.activate();
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
		Level.drawObj.draw(this.hitbox.transform(drawTf));
	}
	
}
