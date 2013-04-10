package com.bipolar.entities;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.bipolar.view.Camera;

public class Ball extends Entity{
	public Ball(Image img, Point p){
		super(img, p);
		
	}
	
	public void update(){
		position.x++;
	}
}
