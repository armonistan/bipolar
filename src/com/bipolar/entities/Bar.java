package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Bar extends Entity{

	public Bar(int xpos, int ypos, boolean solid, boolean state) {
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("bar");
	}

}
