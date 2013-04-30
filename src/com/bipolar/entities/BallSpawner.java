package com.bipolar.entities;

import org.newdawn.slick.geom.Vector2f;

import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class BallSpawner extends Entity {
	
	public BallSpawner(int xpos, int ypos, int drawLayer) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("ballspawner");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}

	public void update() {
	}
	
	public void spawnBall() {
		if (EntityController.ball != null) {
			EntityController.removeEntity(EntityController.ball);
		}
		Ball b = new Ball((int) (this.position.x + this.width / 2 - 5), 
				(int) this.position.y + this.height - 11, new Vector2f(0,0));
		b.setRespawned(true);
	}
	
}
