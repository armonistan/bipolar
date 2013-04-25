package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.resourceloader.ResourceLoader;

public class Gate extends Entity{

	public Gate(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("gate");
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
	}
	
	public void activate() {
		System.out.println("gate is active");
		this.setSolid(false);
	}
	
	public void update() {
		if (this.state == 1) {
			this.activate();
			this.setState(0);
		}
	}

}
