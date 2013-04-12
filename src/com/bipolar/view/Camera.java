package com.bipolar.view;

import java.awt.Polygon;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import com.bipolar.controller.EntityController;
import com.bipolar.entities.*;
import com.bipolar.model.LevelController;

public class Camera {
	
	private Polygon cameraPort;
	int width;
	int height;
	
	public Camera(GameContainer w){
		cameraPort = new Polygon();
		cameraPort.addPoint(0, 0);
		cameraPort.addPoint(w.getWidth(), 0);
		cameraPort.addPoint(0, w.getHeight());
		cameraPort.addPoint(w.getWidth(), w.getHeight());
		
		width = w.getWidth();
		height = w.getHeight();
	}
	
	public void update(){
	}
	
	public void render(){
		for(Entity e: EntityController.levelObjects){
			e.render(cameraPort);
		}
	}

}
