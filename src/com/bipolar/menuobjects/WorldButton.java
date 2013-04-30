package com.bipolar.menuobjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.bipolar.resourceloader.ResourceLoader;

public class WorldButton extends MenuButton{
	
	public int worldID;
	Input in;
	
	public WorldButton(int id, Input in){
		super(ResourceLoader.getAnimation("worldbutton"));
		this.in = in;
		this.setWorld(id);
	}
	
	public WorldButton(Animation anim, int id, Input in){
		super(anim);
		this.in = in;
		this.setWorld(id);
	}
	
	public WorldButton(int x, int y, Animation anim, int id, Input in) {
		super(x, y, anim, in);
		this.setWorld(id);
	}
	
	public void setWorld(int w){
		worldID = w;
	}
	
	public int getWorld(){
		return this.worldID;
	}
	
	@Override
	public boolean getClicked(){
		if (this.hover && this.in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
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
				super.setHover();
			} else {
				super.setUnhover();
			}
		}
	}
	
	@Override
	public void render(Graphics g){
		this.image.draw(this.x, this.y);
		g.setColor(org.newdawn.slick.Color.black);
		g.drawString("World\n" + (worldID + 1), this.x + 25, this.y + 20);
	}
	
}
