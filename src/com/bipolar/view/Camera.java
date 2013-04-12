package com.bipolar.view;

import java.awt.Polygon;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

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
		Polygon player = LevelController.player.getPosition();
		
		for(int i = 0; i < cameraPort.npoints; i++){
			if(cameraPort.xpoints[i] - player.xpoints[i] < (width/3))
				cameraPort.xpoints[i]++;
			else if(cameraPort.xpoints[i] - player.xpoints[i] > (2*width/3))
				cameraPort.xpoints[i]++;
			if(cameraPort.ypoints[i] - player.ypoints[i] < (height/3))
				cameraPort.ypoints[i]++;
			else if(cameraPort.ypoints[i] - player.ypoints[i] < (2*height/3))
				cameraPort.ypoints[i]++;
		}
	}
	
	public void render(){
		for(Entity e: LevelController.levelObjects){
			e.render(cameraPort);
		}
	}

}
