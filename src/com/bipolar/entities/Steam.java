package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Steam extends Entity{

	public Steam(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		// TODO Auto-generated constructor stub
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("steam");
	}

}
