package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Machine extends Entity{

	public Machine(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("Machine");
	}

}
