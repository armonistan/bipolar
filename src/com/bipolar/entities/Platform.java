package com.bipolar.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class Platform extends Entity{

	int width;
	int height;
	Rectangle hitbox;
	
	public Platform(int x, int y) {
		super(x, y);
		image = null;
		this.width = 100;
		this.height = 25;
		this.setGeom(x, y, this.width, this.height);
	}
	
	public Platform(int x, int y, int width, int height, int drawLayer) {
		super(x, y);
		this.drawLayer = drawLayer;
		this.width = width;
		this.height = height;
		this.position = new Vector2f(x, y);
		this.setGeom(x, y, width, height);
	}
	
	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void render(Camera c) {
		transform(c);
		new Transform();
		Transform drawTf = Transform.createTranslateTransform
				(this.transformedPosition.x - this.position.x , this.transformedPosition.y - this.position.y);
		Level.drawObj.setColor(Color.white);
		Level.drawObj.fill(this.hitbox.transform(drawTf));
	}
	
	public void update() {
	}
	
}
