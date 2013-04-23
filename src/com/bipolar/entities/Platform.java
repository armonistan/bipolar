package com.bipolar.entities;

import java.awt.Point;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

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
		// TODO Auto-generated constructor stub
	}
	
	public Platform(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.position = new Point(x, y);
		this.setGeom(x, y, width, height);
	}
	
	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void render(Camera c){
		transform(c);
		new Transform();
		Transform drawTf = Transform.createTranslateTransform
				(this.transformedPosition.x - this.position.x , this.transformedPosition.y - this.position.y);
		Level.drawObj.draw(this.hitbox.transform(drawTf));
	}
	
	public void update(){
		//System.out.println("Hitbox x: " + this.transformedPosition.x + ", y: " + this.transformedPosition.y);
	}
	
}
