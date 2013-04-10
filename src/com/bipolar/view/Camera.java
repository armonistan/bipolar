package com.bipolar.view;

import java.awt.Polygon;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;

import com.bipolar.entities.*;
import com.bipolar.resourceloader.ResourceLoader;

public class Camera {
	
	private static HashSet<Entity> levelEntities;
	private Polygon cameraPort;
	
	public Camera(HashSet<Entity> lE, GameContainer w){
		levelEntities = lE;
		cameraPort = new Polygon();
		cameraPort.addPoint(0, 0);
		cameraPort.addPoint(w.getWidth(), 0);
		cameraPort.addPoint(0, w.getHeight());
		cameraPort.addPoint(w.getWidth(), w.getHeight());
	}
	
	public static void removeEntities(Entity e){
		levelEntities.remove(e);
	}
	
	public static void addEntity(Entity e){
		levelEntities.add(e);
	}
	
	public static HashSet<Entity> getLevelEntities(){
		return levelEntities;
	}
	
	public void update(){
		for(Entity e: levelEntities){
			e.update();
		}
	}
	
	public void render(){
		for(Entity e: levelEntities){
			e.render(cameraPort);
		}
	}

}
