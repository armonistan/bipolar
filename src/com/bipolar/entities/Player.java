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
	private Vector2f velocity, acceleration;
	private Rectangle futureBox;
	public ArrayList<Platform> colliders = new ArrayList<Platform>();
	public final float PLAYER_SPEED = .65f;
	public final int JUMP_HEIGHT = -2;
	public static float delta = 0f;

	public Player(int xpos, int ypos) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("player");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.setGeom(xpos, ypos, this.width, this.height);
		this.futureBox = new Rectangle(xpos, ypos, this.width, this.height);
		this.velocity = new Vector2f();
		this.acceleration = new Vector2f();
		this.acceleration.y = Bipolar.G;
	}

	public static void setInput(Input in) {
		input = in;
	}

	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}

	public void move(){
		if (input.isKeyDown(Input.KEY_W) && this.acceleration.y == 0) {
			this.velocity.y = -1.3f;
			this.acceleration.y = Bipolar.G;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			this.velocity.x = -PLAYER_SPEED * Player.delta;
		} else if (input.isKeyDown(Input.KEY_D)) {
			this.velocity.x = PLAYER_SPEED * Player.delta;
		} else {
			this.acceleration.x = Bipolar.DRAG * Player.delta;
			if(this.velocity.x < -.35f)
				this.velocity.x += this.acceleration.x;
			else if (this.velocity.x > .35f)
				this.velocity.x -= this.acceleration.x;
			else
				this.velocity.x = 0;
		}
		if (this.velocity.y < 6.0f)
			this.velocity.y += this.acceleration.y;

		this.futureBox.setLocation(this.position.x + this.velocity.x, this.position.y + this.velocity.y);
		this.updatePhysics();
	}

	public void updatePhysics(){
		if (this.velocity.x > 0) {
			this.position.x += Math.ceil(this.velocity.x);
		} else {
			this.position.x += Math.floor(this.velocity.x);
		}

		if (this.velocity.y > 0) {
			this.position.y += Math.ceil(this.velocity.y);
		} else {
			this.position.y += Math.floor(this.velocity.y);
		}

		if (!isBlocked(this.futureBox)) {
			this.acceleration.y = Bipolar.G;
		}
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
		Player.delta = LevelController.delta * .001f;
		this.move();
		this.hitbox.setLocation(this.position.x, this.position.y);
	}

	public String toString(){
		return 	("Player x: " + transformedPosition.x + ", y: " + transformedPosition.y);
	}

}
