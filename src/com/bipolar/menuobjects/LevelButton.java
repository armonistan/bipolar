package com.bipolar.menuobjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.bipolar.resourceloader.ResourceLoader;

public class LevelButton extends MenuButton{

	public int worldID;
	public int levelID;
	public Input in;
	
	public LevelButton(int worldID, int levelID, Input in){
		super(ResourceLoader.getAnimation("world" + worldID));
		this.in = in;
		this.worldID = worldID;
		this.levelID = levelID;
	}
	
	public LevelButton(Animation anim, int levelID, int worldID, Input in) {
		super(anim, in);
	}
	
	public int getWorld(){
		return this.worldID;
	}
	
	public void setWorld(int id){
		this.worldID = id;
		this.setAnimation(ResourceLoader.getAnimation("world" + this.worldID));
	}
	
	public void setAnimation(Animation anim){
		this.anim = anim;
	}
	
	@Override
	public boolean getClicked(){
		if (this.anim.getFrame() == 1 
				&& this.in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			return true;
		}
		return false;
	}
	
	@Override
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
	
	@Override
	public void render(Graphics g){
		this.anim.draw(this.x, this.y);
		g.setColor(org.newdawn.slick.Color.black);
		g.drawString("Level\n\t" + (levelID + 1), this.x + 25, this.y + 20);
	}
}
