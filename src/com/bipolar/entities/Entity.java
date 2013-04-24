package com.bipolar.entities;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.view.Camera;
import com.bipolar.resourceloader.ResourceLoader;

public class Entity {
	
	protected int width;
	protected int height;
	
	protected Vector2f position;
	protected Vector2f transformedPosition;
	
	protected Image image;
	protected boolean solid, state;
	
	protected Rectangle hitbox;
	protected Rectangle tfbox;
	
	public Entity(int x, int y) {
		this.position = new Vector2f(x,y);
		this.transformedPosition = new Vector2f(x,y);
		this.image = null;
		this.width = 0;
		this.height = 0;
	}
	
	public Entity(int x, int y, boolean solid, boolean state) {
		this.position = new Vector2f(x, y);
		this.transformedPosition = new Vector2f(x, y);
		this.solid = solid;
		this.state = state;
		this.image = null;
		this.width = 0;
		this.height = 0;
		this.setPosition(position);
		this.hitbox = new Rectangle(x, y, this.width, this.height);
	}
	
	public void setPosition(Vector2f position) {
		this.position.set(position);
	}
	
	public void setTransformedPosition(Vector2f position) {
		this.transformedPosition.set(position);
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
		float transformedX = position.x - c.getCameraPort().getMinX();
		float transformedY = position.y - c.getCameraPort().getMinY();

		transformedPosition.set(transformedX, transformedY);
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
