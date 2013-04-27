package com.bipolar.entities;

import org.newdawn.slick.geom.Vector2f;

public class Field extends Entity{
	
	int strength = 0;
	
	public Field(int x, int y, int state, int strength){
		super(x, y, state);
		this.strength = strength;
	}
	
	public Vector2f getInfluence(Vector2f pos) {
		Vector2f electricComponent;
		Vector2f magneticComponent;
		
		Vector2f rHat = 
		
		return null;
	}

}
