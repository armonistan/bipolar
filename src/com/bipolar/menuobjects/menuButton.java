package com.bipolar.menuobjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;

public class MenuButton {
	
	int x, y, width, height;
	Animation anim;
	Input in;
	
	public MenuButton(Animation anim, Input in){
		this.anim = anim;
		this.anim.stop();
		this.in = in;
		this.width = this.anim.getWidth();
		this.height = this.anim.getHeight();
		setUnhover();
	}
	
	public MenuButton(int x, int y, Animation anim, Input in){
		this.anim = anim;
		this.anim.stop();
		this.in = in;
		this.width = this.anim.getWidth();
		this.height = this.anim.getHeight();
		setPosition(x, y);
		setUnhover();
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setHover(){
		this.anim.setCurrentFrame(1);
	}
	
	public void setUnhover(){
		this.anim.setCurrentFrame(0);
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public boolean getClicked(){
		if (this.anim.getFrame() == 1 
				&& in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			return true;
		}
		return false;
	}
	
	public void render(){
		this.anim.draw(x, y);
	}
	
	public void update(){
		if (in.getAbsoluteMouseX() < this.x + this.width 
				&& in.getAbsoluteMouseX() > this.x
				&& in.getAbsoluteMouseY() < this.y + this.height
				&& in.getAbsoluteMouseY() > this.y) {
			this.setHover();
		} else {
			this.setUnhover();
		}
	}
}