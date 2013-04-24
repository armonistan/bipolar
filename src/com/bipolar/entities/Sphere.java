package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Sphere extends Entity{

	public Sphere(int xpos, int ypos, boolean solid, int state) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("sphere");
	}

}
