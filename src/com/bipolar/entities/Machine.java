package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class Machine extends Entity{
	
	private boolean cool, eSlot, mSlot;
	private long t1;
	private int active, slots, type;
	
	public Machine(int xpos, int ypos, boolean solid, int state, int drawLayer, int type) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("Machine"), 96, 96);
		this.state = state;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
		this.cool = true;
		this.type = type;
		if (this.type == 0) {
			this.mSlot = true;
		} else if (this.type == 1) {
			this.eSlot = true;
		} else {
			this.eSlot = true;
			this.mSlot = true;
		}
		this.active = 0;
	}
	
	public boolean takingE() {
		if (this.type == 1 || this.type == 2 && this.eSlot) {
			return true;
		}
		return false;
	}
	
	public void filledE() {
		this.eSlot = false;
	}
	
	public boolean takingM() {
		if (this.type == 0 || this.type == 2 && this.eSlot) {
			return true;
		}
		return false;
	}
	
	public void filledM() {
		this.mSlot = false;
	}
	
	public boolean cool() {
		return cool;
	}
	
	public void activate() {
		this.setState(1);
		t1 = Bipolar.elapsedTime;
		this.cool = false;
	}
	
	public void update() {
		if (!this.eSlot && !this.mSlot && this.cool()) {
			this.activate();
		}
		
		if (Bipolar.elapsedTime - t1 > 200) {
			this.setState(0);
		}
	}
}
