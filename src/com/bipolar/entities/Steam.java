package com.bipolar.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class Steam extends Entity{

	private long t1;
	private boolean on;
	private Image there;
	
	public Steam(int xpos, int ypos, boolean solid, int state, int drawLayer, boolean on) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("steam");
		this.image = this.image.getScaledCopy(this.image.getWidth(), 
				this.image.getHeight() * state);
		this.there = this.image.copy();
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
		this.cool = true;
		this.on = on;
		if (!this.on) {
			this.image = null;
		}
	}

	public boolean cool() {
		return this.cool;
	}
	
	public boolean on(){
		return this.on;
	}
	
	public void activate() {
		System.out.println("activating steam");
		t1 = Bipolar.elapsedTime;
		this.cool = false;
		this.on = !this.on;
		if (this.on) {
			this.image = this.there.copy();
		} else {
			this.image = null;
		}
	}
	
	public void update() {
		if (!this.cool && (Bipolar.elapsedTime - this.t1 > 1000)) {
			this.cool = true;
		}
	}
}
