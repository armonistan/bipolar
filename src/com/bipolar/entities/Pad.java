package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class Pad extends Entity{
	
	private boolean cool = true;
	private long t1;
	
	public Pad(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("pad"), 84, 16);
		this.state = state;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
	}
	
	public boolean cool() {
		return cool;
	}
	
	public void activate() {
		this.setState(1);
		this.image = this.sheet.getSubImage(1, 0);
		t1 = Bipolar.elapsedTime;
		this.cool = false;
	}
	
	public void update() {
		if (!this.cool && (Bipolar.elapsedTime - this.t1 > 1000)) {
			this.image = this.sheet.getSubImage(0, 0);
			this.cool = true;
			this.setState(0);
		}
	}
}
