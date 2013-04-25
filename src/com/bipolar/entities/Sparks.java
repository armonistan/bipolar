package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Sparks extends Entity{

	public Sparks(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("sparks");
		this.image = this.image.getScaledCopy(this.image.getWidth(), 
				this.image.getHeight() * state);
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
	}

}
