package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class Bar extends Entity{

	private Field field1;
	private Field field2;
	
	public Bar(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("bar");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(xpos, ypos, this.width, this.height);
		this.field1 = new Field(xpos + this.width/2, ypos, this.state, -1);
		this.field2 = new Field(xpos + this.width/2, ypos + this.height, this.state, 1);
		EntityController.addEntity(this.field1);
		EntityController.addEntity(this.field2);
	}
	
	public Vector2f getForce(Vector2f pos){
		Vector2f resultant = new Vector2f();
		resultant.add(this.field1.getInfluence(pos));
		resultant.add(this.field2.getInfluence(pos));
		return resultant;
	}

}
