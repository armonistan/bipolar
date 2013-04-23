package com.bipolar.entities;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.bipolar.view.Camera;
import com.bipolar.resourceloader.ResourceLoader;

public class Entity {
	
	protected int width;
	protected int height;
	
	protected Point position;
	protected Point transformedPosition;
	
	protected Image image;
	protected boolean solid, state;
	
	protected Rectangle hitbox;
	protected Rectangle tfbox;
	
	public Entity(int x, int y) {
		this.position = new Point(x,y);
		this.transformedPosition = new Point(x,y);
		this.image = null;
		this.width = 0;
		this.height = 0;
	}
	
	public Entity(int x, int y, boolean solid, boolean state) {
		this.position = new Point(x, y);
		this.transformedPosition = new Point(x, y);
		this.solid = solid;
		this.state = state;
		this.image = null;
		this.width = 0;
		this.height = 0;
		this.setPosition(position);
		this.hitbox = new Rectangle(x, y, this.width, this.height);
	}
	
	public void setPosition(Point p) {
		position = p;
	}
	
	public void setTransformedPosition(Point p) {
		transformedPosition = p;
	}
	
	public void update() {
	}
	
	public void render(Camera c) {
		transform(c);
		if (image != null) {
			image.draw(transformedPosition.x, transformedPosition.y);
		}
	}
	
	public void transform(Camera c) {
		int transformedX = (int) (position.x - c.getCameraPort().getMinX());
		int transformedY = (int) (position.y - c.getCameraPort().getMinY());

		transformedPosition.setLocation(transformedX, transformedY);
	}
	
	public Rectangle getPosition() {
		setPosition(position);
		
		return hitbox;
	}
	
	public Rectangle getTransformedPosition() {
		setTransformedPosition(position);
		
		return hitbox;
	}
}
