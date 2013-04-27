package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Bar extends Entity{

	public Bar(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("bar");
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
	}

}
