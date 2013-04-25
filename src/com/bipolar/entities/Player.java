package com.bipolar.entities;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
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
	private static final float JUMP_EPSILON = .01f;
	static Input input;
	private Vector2f velocity, acceleration;
	private Rectangle futureBox;
	private boolean holdingBall = false;
	public ArrayList<Platform> colliders = new ArrayList<Platform>();
	public final float PLAYER_SPEED = 200f;
	public final int JUMP_HEIGHT = -2;
	public static float delta = .001f;

	public Player(int xpos, int ypos, int drawLayer) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("player");
		this.drawLayer = drawLayer;
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

	public void move() {
		if (input.isKeyDown(Input.KEY_A)) {
			this.velocity.x = -PLAYER_SPEED * Player.delta;
		} else if (input.isKeyDown(Input.KEY_D)) {
			this.velocity.x = PLAYER_SPEED * Player.delta;
		} else {
			this.acceleration.x = Bipolar.DRAG * Player.delta;
			if(this.velocity.x < -.55f)
				this.velocity.x += this.acceleration.x;
			else if (this.velocity.x > .55f)
				this.velocity.x -= this.acceleration.x;
			else
				this.velocity.x = 0f;
		}
		if (this.velocity.y < 6.0f) {
			this.velocity.y += this.acceleration.y;
		}

		this.futureBox.setLocation(this.position.x + (this.velocity.x), this.position.y + (this.velocity.y));
		Rectangle underBlock = isBlocked(this.futureBox);
		if((underBlock != null) && (underBlock.getMinY() + JUMP_EPSILON > this.hitbox.getMaxY()) && (input.isKeyDown(Input.KEY_W))) {
			this.velocity.y = -1.8f;
		}
		do {
			this.fixVelocity(isBlocked(this.futureBox));
			this.futureBox.setLocation(this.position.x + (this.velocity.x), this.position.y + (this.velocity.y));
		} while(isBlocked(this.futureBox) != null);
		this.updatePhysics();
	}

	public void fixVelocity(Rectangle collideBox) {
		if(collideBox == null) {
			return;
		}
		boolean toFix; /*False == x, True == y*/
		if(this.velocity.x == 0) {
			toFix = true;
		} else if(this.velocity.y == 0) {
			toFix = false;
		} else {
			int velQuad = quadrant(this.velocity);
			Vector2f myCorner = getQuadrantCorner(this.hitbox, velQuad);
			int oppositeQuad;
			switch(velQuad) {
			case 1:
				oppositeQuad = 3;
				break;
			case 2:
				oppositeQuad = 4;
				break;
			case 3:
				oppositeQuad = 1;
				break;
			default:
				oppositeQuad = 2;
			}
			Vector2f itsCorner = getQuadrantCorner(collideBox, oppositeQuad);
			Vector2f myCornerToItsCorner = itsCorner.sub(myCorner);
			float cross = myCornerToItsCorner.x * this.velocity.y - myCornerToItsCorner.y * this.velocity.x;
			toFix = (cross * velocity.x * velocity.y < 0);
		}
		float high;
		float low = 0f;
		float middle = low;
		if(toFix) {
			high = this.velocity.y;
		} else {
			high = this.velocity.x;
		}

		for (int i = 0; i < 10; i++) {
			middle = (high + low) / 2f;
			if(toFix) {
				this.futureBox.setY(this.position.y + middle);
			} else {
				this.futureBox.setX(this.position.x + middle);
			}
			if (isBlocked(this.futureBox) != null) {
				high = middle;
			} else {
				low = middle;
			}
		}
		if(toFix) {
			this.velocity.y = low;
		} else {
			this.velocity.x = low;
		}
	}

	public int quadrant(Vector2f vector) {
		if(vector.x < 0) {
			if(vector.y < 0) {
				return 3;
			}
			return 2;
		}
		if(vector.y < 0) {
			return 4;
		}
		return 1;
	}

	public Vector2f getQuadrantCorner(Rectangle rect, int quadrant) {
		switch(quadrant) {
		case 1:
			return new Vector2f(rect.getMaxX(), rect.getMaxY());
		case 2:
			return new Vector2f(rect.getMinX(), rect.getMaxY());
		case 3:
			return new Vector2f(rect.getMinX(), rect.getMinY());
		default:
			return new Vector2f(rect.getMaxX(), rect.getMinY());
		}
	}

	public void updatePhysics(){
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
	}

	public Rectangle isBlocked(Shape tf){
		for (Entity e : EntityController.getLevelObjects()) {
			if (e instanceof Platform) {
				Platform p = (Platform) e;
				Rectangle r = p.hitbox;
				if (tf.intersects(r)) {
					return r;
				}
			} else if (e.solid) {
				if (tf.intersects(e.hitbox)) {
					if (e instanceof Pad) {
						Pad p = (Pad) e;
						p.setState(1);
					}
					return e.hitbox;
				}
			} else if (e instanceof Ball) {
				if (tf.intersects(e.hitbox)) {
					Ball b = (Ball) e;
					if (b.getVelocity().x == 0 && b.getVelocity().y == 0
							&& b.getRespawned()) {
						holdingBall = true;
						EntityController.removeEntity(e);
					}
				}
			}
		}
		return null;
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
		Level.drawObj.draw(this.futureBox.transform(drawTf));
		this.image.draw(this.transformedPosition.x, this.transformedPosition.y);
	}

	public void update(){
		Player.delta = LevelController.delta * .001f;
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && Bipolar.slowdown < 10) {
			Bipolar.slowdown++;
		} else {
			if (Bipolar.slowdown != 1) {
				Bipolar.slowdown--;
			}
		}

		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (holdingBall) {
				Vector2f mousePos = new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY());
				Vector2f onscreenPos = EntityController.player.transformedPosition.add(new Vector2f(EntityController.player.hitbox.getWidth() / 2, EntityController.player.hitbox.getHeight() / 2));
				Vector2f direction = mousePos.sub(onscreenPos).normalise();
				new Ball((int) this.hitbox.getCenterX(), (int) this.hitbox.getCenterY(), direction);
				holdingBall = false;
			} else {
				EntityController.ballSpawner.spawnBall();
			}
		}

		this.move();

		if (this.position.x > Bipolar.MAXWIDTH
				|| this.position.x < -Bipolar.MAXWIDTH
				|| this.position.y > Bipolar.MAXHEIGHT
				|| this.position.y < -Bipolar.MAXHEIGHT) {
			this.position.set(EntityController.playerSpawner.position);
			this.velocity.set(0, 0);
			this.hitbox.setLocation(this.position.x, this.position.y);
			LevelController.camera.snapToPlayer();
		}

		this.hitbox.setLocation(this.position.x, this.position.y);
	}

	public String toString(){
		return 	("Player x: " + position.x + ", y: " + position.y);
	}

}
