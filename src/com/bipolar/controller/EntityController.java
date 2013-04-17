package com.bipolar.controller;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import com.bipolar.entities.Ball;
import com.bipolar.entities.Entity;
import com.bipolar.entities.Player;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.view.Camera;

public class EntityController {
	public static ArrayList<Entity> levelObjects = new ArrayList<Entity>();
	public static Player player;
	
	public static void addEntity(Entity e){
		EntityController.levelObjects.add(e);
	}
	
	public static void setPlayer(Player p){
		player = p;
	}
	
	public static void deleteAllEntities(){
		for (int i = 0; i < EntityController.levelObjects.size(); i++) {
			//tell each entity to delete itself
		}
		EntityController.levelObjects.clear();
	}
	
	public static void update(){
		for (Entity levelObject: levelObjects) {
			levelObject.update();
		}
	}
	
	public static void render(Camera c){
		for (Entity levelObject: levelObjects) {
			levelObject.render(c);
		}
	}
}
