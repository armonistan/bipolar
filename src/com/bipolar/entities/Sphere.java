package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class Sphere extends Entity{
	
	private Field field;
	
	public Sphere(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.image = ResourceLoader.getImage("sphere");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(xpos, ypos, this.width, this.height);
		this.field = new Field((int) this.hitbox.getCenterX(), 
				(int) this.hitbox.getCenterY(), this.state, -1);
		EntityController.addEntity(this.field);
	}
	
	public Vector2f getForce(Vector2f pos){
		return this.field.getInfluence(pos);
	}

}
