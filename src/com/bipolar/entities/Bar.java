package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Bar extends Entity{

	public Bar(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("bar");
	}

}
