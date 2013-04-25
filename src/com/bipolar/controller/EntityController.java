package com.bipolar.controller;

import java.util.HashMap;
import java.util.HashSet;

import com.bipolar.Bipolar;
import com.bipolar.entities.Ball;
import com.bipolar.entities.BallSpawner;
import com.bipolar.entities.Entity;
import com.bipolar.entities.Player;
import com.bipolar.entities.PlayerSpawner;
import com.bipolar.view.Camera;

public class EntityController {
	private static HashMap<Integer, HashSet<Entity>> renderables = new HashMap<Integer, HashSet<Entity>>(Bipolar.LAYERS);
	private static HashSet<Entity> levelObjects = new HashSet<Entity>();
	public static HashSet<Entity> toAdd = new HashSet<Entity>();
	public static HashSet<Entity> toRemove = new HashSet<Entity>();
	public static Player player;
	public static PlayerSpawner playerSpawner;
	public static BallSpawner ballSpawner;

	public static void addEntity(Entity e) {
		EntityController.toAdd.add(e);
	}

	public static void addBall(Ball b) {
		for(Entity e : EntityController.getLevelObjects()) {
			if(e instanceof Ball) {
				removeEntity(e);
			}
		}
		addEntity(b);
	}

	public static void removeEntity(Entity e) {
		EntityController.toRemove.add(e);
	}

	public static void setPlayer(Player p) {
		EntityController.player = p;
	}

	public static void setPlayerSpawner(PlayerSpawner spawner) {
		EntityController.playerSpawner = spawner;
	}

	public static void setBallSpawner(BallSpawner spawner) {
		EntityController.ballSpawner = spawner;
	}

	public static void deleteAllEntities() {
		EntityController.getLevelObjects().clear();
		EntityController.renderables.clear();
	}

	public static void enter() {
		for (int i = 0; i < Bipolar.LAYERS; i++){
			EntityController.renderables.put(i, new HashSet<Entity>());
		}
	}

	public static void update() {
		if (toRemove != null && renderables != null) {
			for (Entity removeObject : toRemove) {
				EntityController.getLevelObjects().remove(removeObject);
				EntityController.renderables.get(removeObject.getDrawLayer()).remove(removeObject);
			}
			EntityController.toRemove.clear();
		}
		if (toAdd != null && renderables != null) {
			for (Entity addObject : toAdd) {
				EntityController.getLevelObjects().add(addObject);
				EntityController.renderables.get(addObject.getDrawLayer()).add(addObject);
			}
			toAdd.clear();
		}
		for (Entity levelObject: EntityController.getLevelObjects()) {
			levelObject.update();
		}
	}

	public static void render(Camera c) {
		for (int i = 0; i < Bipolar.LAYERS; i++) {
			if (EntityController.renderables.get(i) != null) {
				for (Entity e : EntityController.renderables.get(i)) {
					e.render(c);
				}
			}
		}
	}

	public static HashSet<Entity> getLevelObjects() {
		return levelObjects;
	}
}
