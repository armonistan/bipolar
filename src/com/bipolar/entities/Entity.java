package com.bipolar.entities;

public abstract class Entity {
	int x, y;
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	abstract void render();
	abstract void update();
}
