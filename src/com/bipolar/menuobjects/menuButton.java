package com.bipolar.menuobjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class MenuButton {
	
	int x, y, width, height;
	Animation anim;
	Image image = null;
	Input in;
	protected boolean hover = false;
	
	public MenuButton(Animation anim){
		this.anim = anim;
		this.anim.stop();
		this.image = this.anim.getImage(0).copy();
		this.in = null;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		setUnhover();
	}
	
	public MenuButton(Animation anim, Input in){
		this.anim = anim;
		this.anim.stop();
		this.image = this.anim.getImage(0).copy();
		this.in = in;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		setUnhover();
	}
	
	public MenuButton(int x, int y, Animation anim, Input in){
		this.anim = anim;
		this.anim.stop();
		this.image = this.anim.getImage(0).copy();
		this.in = null;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		setPosition(x, y);
		setUnhover();
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setHover(){
		this.hover = true;
		this.image = this.anim.getImage(1).copy();
	}
	
	public void setUnhover(){
		this.hover = false;
		this.image = this.anim.getImage(0).copy();
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean getClicked(){
		if (this.hover && this.in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			return true;
		}
		return false;
	}
	
	public void render(Graphics g){
		this.image.draw(x, y);
	}
	
	public void update(){
		if (this.x != -1 && this.y != -1) {
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
}
