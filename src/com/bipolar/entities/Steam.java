package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Steam extends Entity{

	public Steam(int xpos, int ypos, boolean solid, boolean state) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state);
		this.image = ResourceLoader.getImage("steam");
	}

}
