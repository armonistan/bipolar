package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Fuse extends Entity{

	public Fuse(int xpos, int ypos, boolean solid, boolean state) {
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("fuse");
		// TODO Auto-generated constructor stub
	}

}
