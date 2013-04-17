package com.bipolar.view;

import java.awt.Polygon;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import com.bipolar.controller.EntityController;
import com.bipolar.entities.*;
import com.bipolar.model.LevelController;

public class Camera {
	
	private Polygon cameraPort;
	public int width;
	public int height;
	
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
		Polygon player = EntityController.player.getPosition();
		//System.out.println(EntityController.player + "\n");

		
		if(player.xpoints[0] <= cameraPort.xpoints[0] + (int)(width/5))
			moveCameraPortX(-1);
		else if(player.xpoints[1] >= cameraPort.xpoints[1] - (int)(width/5))
			moveCameraPortX(1);
		if(player.ypoints[0] <= cameraPort.ypoints[0] + (int)(height/5))
			moveCameraPortY(-1);
		else if(player.ypoints[2] >= cameraPort.ypoints[2] - (int)(height/5))
			moveCameraPortY(1);
	}
	
	private void moveCameraPortX(int d){
		for(int j = 0; j < cameraPort.npoints; j++)
			cameraPort.xpoints[j] += d;
	}
	
	private void moveCameraPortY(int d){
		for(int j = 0; j < cameraPort.npoints; j++)
			cameraPort.ypoints[j] += d;
	}
	
	public Polygon getCameraPort(){
		return cameraPort;
	}

}
