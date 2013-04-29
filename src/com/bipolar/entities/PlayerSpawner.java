package com.bipolar.entities;

import org.newdawn.slick.geom.Vector2f;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.model.LevelController;
import com.bipolar.resourceloader.ResourceLoader;

public class PlayerSpawner extends Entity{

	private int animTime = 3;
	private long t1;
	
	public PlayerSpawner(int x, int y, int drawLayer) {
		super(x, y);
		this.drawLayer = drawLayer;
		setSpriteSheet(ResourceLoader.getImage("playerspawner"), 48, 72);
	}
	
	public void spawnPlayer() {
		EntityController.player.setPosition(new Vector2f(this.position.x + this.width / 2, this.position.y));
		EntityController.player.setVelocity(new Vector2f(0, 0));
		EntityController.player.setHitboxPos(this.position.x + this.width / 2, this.position.y);
		if (!LevelController.getCameraFocus()) {
			LevelController.camera.snapToPlayer();
		}
		this.toggleAnimation();
		this.t1 = Bipolar.elapsedTime;
	}
	
	public void update() {
		if (this.animating && (Bipolar.elapsedTime - this.t1 > this.animTime * 1000)) {
			this.toggleAnimation();
		}
	}
}
