package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Fuse extends Entity{

	public Fuse(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("fuse");
		this.state = state;
		this.hitbox = new Rectangle(this.position.x, this.position.y,
				this.image.getWidth(), this.image.getHeight());
		// TODO Auto-generated constructor stub
	}
	
	public int getState() {
		return this.state;
	}

}
