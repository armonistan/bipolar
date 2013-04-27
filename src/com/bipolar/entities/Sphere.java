package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Sphere extends Entity{

	public Sphere(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("sphere");
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
	}

}
