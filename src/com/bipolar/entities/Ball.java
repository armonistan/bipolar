package com.bipolar.entities;

import com.bipolar.resourceloader.ResourceLoader;

public class Ball extends Entity{
	public Ball(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("ball");
	}
	
	public void update(){
		
	}
}
