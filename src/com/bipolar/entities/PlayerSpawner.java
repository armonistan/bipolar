package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class PlayerSpawner extends Entity{

	public PlayerSpawner(int x, int y, int drawLayer) {
		super(x, y);
		this.image = ResourceLoader.getImage("playerspawner");
		// TODO Auto-generated constructor stub
	}
	
	
}
