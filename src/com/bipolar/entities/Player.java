package com.bipolar.entities;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.bipolar.resourceloader.ResourceLoader;

public class Player extends Entity{
	
	static Input input;
	
	public Player(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("tempball");
	}
	
	public static void setInput(Input in) {
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
	
	public String toString(){
		return 	("x: " + position.x + ", y: " + position.y);
	}

}
