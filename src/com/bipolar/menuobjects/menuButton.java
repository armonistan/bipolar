package com.bipolar.menuobjects;

import org.newdawn.slick.Animation;

public class menuButton {
	
	int x, y;
	Animation anim;
	
	public menuButton(int x, int y, Animation anim){
		this.x = x;
		this.y = y;
		this.anim = anim;
		this.anim.stop();
	}
	
	public void setHover(){
		this.anim.setCurrentFrame(1);
	}
	
	public void setUnhover(){
		this.anim.setCurrentFrame(0);
	}

}
