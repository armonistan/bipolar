package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

public class Platform extends Entity{

	int width;
	int height;
	Rectangle hitbox;
	
	public Platform(int x, int y) {
		super(x, y);
		this.width = 100;
		this.height = 25;
		this.setGeom(x, y, this.width, this.height);
		// TODO Auto-generated constructor stub
	}
	
	public Platform(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.setGeom(x, y, width, height);
	}
	
	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void render(){
		
	}
	
}