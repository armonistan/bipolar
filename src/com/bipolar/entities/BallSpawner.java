package com.bipolar.entities;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.view.Camera;

public class BallSpawner extends Entity {
	
	public BallSpawner(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("ballspawner");
	}

	public void update(){
	}
	
}
