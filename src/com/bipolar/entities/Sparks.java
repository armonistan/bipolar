package com.bipolar.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class Sparks extends Entity{

	private long t1;
	private boolean on;
	private Image there;
	
	public Sparks(int xpos, int ypos, boolean solid, int state, int drawLayer, boolean on) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("sparks"), 8, 50);
		this.width = 8;
		this.height = 50 * state;
		this.there = this.image.copy();
		this.hitbox = new Rectangle(xpos, ypos, this.width, this.height);
		this.cool = true;
		this.on = on;
		if (!this.on) {
			this.image = null;
		} else {
			this.toggleAnimation();
		}
	}
	
	public boolean cool() {
		return this.cool;
	}
	
	public boolean on(){
		return this.on;
	}
	
	public void activate() {
		t1 = Bipolar.elapsedTime;
		this.cool = false;
		this.on = !this.on;
		if (this.on) {
			this.image = this.there.copy();
			this.toggleAnimation();
		} else {
			this.image = null;
			this.toggleAnimation();
		}
	}
	
	public void update() {
		if (!this.cool && (Bipolar.elapsedTime - this.t1 > 1000)) {
			this.cool = true;
		}
	}
	
}
