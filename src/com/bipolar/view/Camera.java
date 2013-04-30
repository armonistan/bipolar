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
		this.snapToPlayer();
	}
	
	public void snapToPlayer() {
		Rectangle player = EntityController.player.getPosition();
		this.cameraPort.setCenterX(player.getCenterX());
		this.cameraPort.setCenterY(player.getCenterY());
		this.innerBox.setCenterX(player.getCenterX());
		this.innerBox.setCenterY(player.getCenterY());
	}
	
	public void snapToBall() {
		Rectangle player = EntityController.ball.getPosition();
		this.cameraPort.setCenterX(player.getCenterX());
		this.cameraPort.setCenterY(player.getCenterY());
		this.innerBox.setCenterX(player.getCenterX());
		this.innerBox.setCenterY(player.getCenterY());
	}
	
	public void update(boolean mode) {
		Rectangle rect = null;
		if (mode) {
			rect = EntityController.ball.getPosition();
			dx = EntityController.ball.getVelocity().x;
			dy = EntityController.ball.getVelocity().y;
		} else {
			rect = EntityController.player.getPosition();
			dx = EntityController.player.getVelocity().x;
			dy = EntityController.player.getVelocity().y;
		}
		
		if (rect.getMinX() < innerBox.getMinX()) {
			this.moveCameraPortX(dx);
		}
		if (rect.getMaxX() > innerBox.getMaxX()) {
			this.moveCameraPortX(dx);
		}
		if (rect.getMinY() < innerBox.getMinY()) {
			this.moveCameraPortY(dy);
		}
		if (rect.getMaxY() > innerBox.getMaxY()) {
			this.moveCameraPortY(dy);
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
