package com.bipolar.entities;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends Entity{
	
	Input input;
	
	public Player(Image img, Point p, Input in){
		super(img, p);
		input = in;
	}
	
	public void update(){
		if(input.isKeyDown(input.KEY_S))
			position.y++;
		else if(input.isKeyDown(input.KEY_W))
			position.y--;
		else if(input.isKeyDown(input.KEY_A))
			position.x--;
		else if(input.isKeyDown(input.KEY_D))
			position.x++;
	}

}
