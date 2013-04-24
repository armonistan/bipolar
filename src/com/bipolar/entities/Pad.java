package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Pad extends Entity{

	public Pad(int xpos, int ypos, boolean solid, int state) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("pad");
	}

}
