package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.resourceloader.ResourceLoader;

public class Ball extends Entity{
	
	private int state = -1; //netural
	private Vector2f velocity, acceleration;
	private Rectangle futureBox;
	
	public Ball(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("ball");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.setGeom(xpos, ypos, this.width, this.height);
		this.futureBox = new Rectangle(xpos, ypos, this.width, this.height);
		this.velocity = new Vector2f();
		this.acceleration = new Vector2f();
		this.acceleration.y = 0;//Bipolar.G;
	}
	
	public void update(){
		
	}
	
	public boolean isBlocked(Shape tf){
		for (Entity e : EntityController.levelObjects) {
			if (e instanceof Platform) {
				Platform p = (Platform) e;
				if (tf.intersects(p.hitbox)) {
					this.acceleration.y = 0;
					if (this.velocity.y >= 0) {
						this.velocity.y = 0;
						this.position.y = (int) (p.hitbox.getMinY() - this.hitbox.getHeight());
					} else {
						if (tf.getMinX() <= p.hitbox.getMinX()
								&& tf.getMinY() > p.hitbox.getMaxY()) {
							this.velocity.x = 0;
							this.position.x = (int) (p.hitbox.getMinX() - tf.getWidth());
							return false;
						} else if (tf.getMaxX() >= p.hitbox.getMaxX()
								&& tf.getMaxY() < p.hitbox.getMinY()) {
							this.velocity.x = 0;
							this.position.x = (int) p.hitbox.getMaxX();
							return false;
						} else if (tf.getMinY() <= p.hitbox.getMaxY()
								&& tf.getMaxX() > p.hitbox.getMinX()) {
							this.acceleration.y = Bipolar.G;
							this.velocity.y = 0;
							this.position.y = (int) (p.hitbox.getMaxY());
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
}
