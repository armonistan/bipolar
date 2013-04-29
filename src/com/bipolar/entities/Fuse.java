package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class Fuse extends Entity{

	private boolean active = false;

	public Fuse(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		if (state == 0) {
			this.image = ResourceLoader.getImage("mfuse");
		} else {
			this.image = ResourceLoader.getImage("efuse");
		}
		this.state = state;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
	}

	public int getState() {
		return this.state;
	}

	public void setActive() {
		this.active = true;
		this.image = ResourceLoader.getImage("closedfuse");
		EntityController.ballSpawner.spawnBall();
	}

	public boolean getActive() {
		return this.active;
	}

}
