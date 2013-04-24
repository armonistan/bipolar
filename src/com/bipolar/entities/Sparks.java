package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Sparks extends Entity{

	public Sparks(int xpos, int ypos, boolean solid, int state) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("sparks");
	}

}
