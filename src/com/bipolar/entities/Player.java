package com.bipolar.entities;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.model.LevelController;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class Player extends Entity{
	
	static Input input;
	private Vector2f velocity;
	private Rectangle futureBox;
	public ArrayList<Platform> colliders = new ArrayList<Platform>();
	int i = 0;
	
	public Player(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("player");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.setGeom(xpos, ypos, this.width, this.height);
		this.futureBox = new Rectangle(xpos, ypos, this.width, this.height);
		this.velocity = new Vector2f();
	}
	
	public static void setInput(Input in) {
		input = in;
	}
	
	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void move(){
		if (input.isKeyDown(Input.KEY_W)) {
			this.velocity.y = -1 * LevelController.delta;
		} else if (input.isKeyDown(Input.KEY_S)) {
			this.velocity.y = 1 * LevelController.delta;
		} else {
			this.velocity.y = 0;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			this.velocity.x = -1 * LevelController.delta;
		} else if (input.isKeyDown(Input.KEY_D)) {
			this.velocity.x = 1 * LevelController.delta;
		} else {
			this.velocity.x = 0;
		}
		
		this.updatePhysics();
	}
	
	public void updatePhysics(){
		new Transform();
		Transform.createTranslateTransform
				(this.position.x + this.velocity.x,
				 this.position.y);
		new Transform();
		Transform.createTranslateTransform
				(this.position.x,
				 this.position.y + this.velocity.y);
		
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
		
		if (isBlocked(this.futureBox)) {
		}
	}
	
	public boolean isBlocked(Shape tf){
		for (Entity e : EntityController.levelObjects) {
			if (e instanceof Platform) {
				Platform p = (Platform) e;
				if (tf.intersects(p.hitbox)) {
					this.velocity.y = 0;
					this.position.y = (int) (p.hitbox.getMinY() - this.hitbox.getHeight());
					return true;
				}
			}
		}
		return false;
	}
	
	public Vector2f getVelocity(){
		return this.velocity;
	}
	
	public void render(Camera c){
		transform(c);
		new Transform();
		Transform drawTf = Transform.createTranslateTransform
				(this.transformedPosition.x - this.position.x , this.transformedPosition.y - this.position.y);
		Level.drawObj.draw(this.hitbox.transform(drawTf));
		this.image.draw(this.transformedPosition.x, this.transformedPosition.y);
	}
	
	public void update(){
		this.move();
		this.hitbox.setLocation(this.position.x, this.position.y);
		this.futureBox.setLocation(this.position.x + this.velocity.x, this.position.y + this.velocity.y);
		//System.out.println(this.toString());
	}
	
	public String toString(){
		return 	("Player x: " + transformedPosition.x + ", y: " + transformedPosition.y);
	}

}
