package com.bipolar.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.view.Camera;

public class Entity {
	
	protected int width;
	protected int height;
	protected int state;
	protected int drawLayer;
	
	protected Vector2f position;
	protected Vector2f transformedPosition;
	
	protected Image image;
	protected boolean solid;
	
	protected Rectangle hitbox;
	protected Rectangle tfbox;
	
	public Entity(int x, int y) {
		this.position = new Vector2f(x,y);
		this.transformedPosition = new Vector2f(x,y);
		this.image = null;
		this.width = 0;
		this.height = 0;
	}
	
	public Entity(int x, int y, boolean solid, int state, int drawLayer) {
		this.position = new Vector2f(x, y);
		this.transformedPosition = new Vector2f(x, y);
		this.solid = solid;
		this.state = state;
		this.image = null;
		this.drawLayer = drawLayer;
		this.width = 0;
		this.height = 0;
		this.setPosition(position);
		this.hitbox = new Rectangle(x, y, this.width, this.height);
	}
	
	public void update() {
	}
	
	public void setPosition(Vector2f position) {
		this.position.set(position);
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	public void setTransformedPosition(Vector2f position) {
		this.transformedPosition.set(position);
	}
	
	public Rectangle isBlocked(Shape tf){
		return null;
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
	
	public int getState(){
		return this.state;
	}
	
	public void activate(){
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public Rectangle getPosition() {
		setPosition(position);
		
		return this.hitbox;
	}
	
	public Rectangle getTransformedPosition() {
		setTransformedPosition(position);
		
		return this.hitbox;
	}
	
	public Vector2f getPos() {
		return this.position;
	}
	
	public int getDrawLayer() {
		return this.drawLayer;
	}
}
