package com.bipolar.controller;

import java.util.ArrayList;

import com.bipolar.entities.Entity;

public class EntityController {
	public static ArrayList<Entity> levelObjects = new ArrayList<Entity>();
	
	public static void addEntity(Entity e){
		EntityController.levelObjects.add(e);
	}
	
	public static void deleteAllEntities(){
		for (int i = 0; i < EntityController.levelObjects.size(); i++) {
			//tell each entity to delete itself
		}
		EntityController.levelObjects.clear();
	}
	
	public static void update(){
		for (int i = 0; i < EntityController.levelObjects.size(); i++) {
			//tell each entity to update itself
		}
	}
	
	public static void render(){
		for (int i = 0; i < EntityController.levelObjects.size(); i++) {
			//tell each entity to render itself
		}
	}
}
