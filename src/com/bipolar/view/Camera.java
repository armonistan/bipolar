package com.bipolar.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;

public class Camera {
	
	public Rectangle cameraPort;
	public Rectangle innerBox;
	public int width;
	public int height;
	
	private float dx, dy;
	
	public Camera(GameContainer w) {
		this.height = Bipolar.HEIGHT;
		this.width = Bipolar.WIDTH;
		cameraPort = new Rectangle(0, 0, this.width, this.height);
		innerBox = new Rectangle( this.width / 4, this.height / 4, 
				this.width / 2, this.height / 2);
	}
	
	public void enter() {
		Rectangle player = EntityController.player.getPosition();
		this.cameraPort.setCenterX(player.getCenterX());
		this.cameraPort.setCenterY(player.getCenterY());
		this.innerBox.setCenterX(player.getCenterX());
		this.innerBox.setCenterY(player.getCenterY());
	}
	
	public void update() {
		Rectangle player = EntityController.player.getPosition();
		dx = EntityController.player.getVelocity().x;
		dy = EntityController.player.getVelocity().y;
		
		if (player.getMinX() < innerBox.getMinX()) {
			this.moveCameraPortX(dx - 1);
		}
		if (player.getMaxX() > innerBox.getMaxX()) {
			this.moveCameraPortX(dx + 1);
		}
		if (player.getMinY() < innerBox.getMinY()) {
			this.moveCameraPortY(dy - 1);
		}
		if (player.getMaxY() > innerBox.getMaxY()) {
			this.moveCameraPortY(dy + 1);
		}
	}
	
	private void moveCameraPortX(float d) {
		this.cameraPort.setLocation(cameraPort.getX() + d, cameraPort.getY());
		this.innerBox.setLocation(innerBox.getX() + d, innerBox.getY());
	}
	
	private void moveCameraPortY(float d) {
		this.cameraPort.setLocation(cameraPort.getX(), cameraPort.getY() + d);
		this.innerBox.setLocation(innerBox.getX(), innerBox.getY() + d);
	}
	
	public Rectangle getCameraPort() {
		return cameraPort;
	}

}
