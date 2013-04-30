package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class Sphere extends Entity{
	
	private Field field;
	private long t1;
	
	public Sphere(int xpos, int ypos, boolean solid, int state, int drawLayer, int flipped) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("sphere"), 65, 65);
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(xpos, ypos, this.width, this.height);
		this.field = new Field((int) this.hitbox.getCenterX(), 
				(int) this.hitbox.getCenterY(), this.state, -1);
		if (this.state == 0) {
			this.image = this.anim.getImage(1);
		} else {
			this.image = this.anim.getImage(0);
		}
		EntityController.addEntity(this.field);
		if (flipped == 1) {
			this.activate();
		}
		this.cool = true;
	}
	
	public Vector2f getForce(Vector2f pos){
		return this.field.getInfluence(pos);
	}
	
	public boolean cool() {
		return cool;
	}
	
	public void activate() {
		this.cool = false;
		t1 = Bipolar.elapsedTime;
		this.field.invertCharge();
	}
	
	public void update() {
		if (!this.cool && (Bipolar.elapsedTime - this.t1 > 1000)) {
			this.cool = true;
		}
	}
}
