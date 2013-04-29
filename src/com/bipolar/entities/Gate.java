package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Gate extends Entity{

	private boolean activated;
	
	public Gate(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("gate"), 16, 156);
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
		this.state = 0;
		this.activated = false;
	}
	
	public void activate() {
		if (!this.activated) {
			this.setSolid(false);
			this.toggleAnimation();
			this.activated = true;
		}
	}
	
	public void update() {
		if (this.animating) {
			this.anim.stopAt(this.anim.getFrameCount());
			this.toggleAnimation();
		}
		if (this.state == 1) {
			this.activate();
		}
	}

}
