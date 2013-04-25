package com.bipolar.entities;

import org.newdawn.slick.Color;
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

public class Ball extends Entity{
	private static final float INIT_VEL = .5f;
	private int state = 1; //neutral
	private Vector2f velocity, acceleration;
	private Rectangle futureBox;
	private boolean respawned = true;

	public Ball(int xpos, int ypos, Vector2f initDirection) {
		super(xpos, ypos);
		this.image = ResourceLoader.getImage("ball");
		this.drawLayer = 8;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.setGeom(xpos, ypos, this.width, this.height);
		this.futureBox = new Rectangle(xpos, ypos, this.width, this.height);
		this.velocity = initDirection.scale(INIT_VEL);
		this.acceleration = new Vector2f();
		EntityController.addBall(this);
	}

	public void update(){
		move();
		this.hitbox.setLocation(this.position.x, this.position.y);

		if (this.position.x > Bipolar.MAXWIDTH
				|| this.position.x < -Bipolar.MAXWIDTH
				|| this.position.y > Bipolar.MAXHEIGHT
				|| this.position.y < -Bipolar.MAXHEIGHT) {
			EntityController.ballSpawner.spawnBall();
			this.hitbox.setLocation(this.position.x, this.position.y);
		}
	}


	public void render(Camera c) {
		transform(c);
		new Transform();
		Transform drawTf = Transform.createTranslateTransform
				(this.transformedPosition.x - this.position.x , this.transformedPosition.y - this.position.y);
		Level.drawObj.setColor(Color.black);
		Level.drawObj.draw(this.hitbox.transform(drawTf));
		Level.drawObj.draw(this.futureBox.transform(drawTf));
		this.image.draw(this.transformedPosition.x, this.transformedPosition.y);
	}


	public void move() {
		this.futureBox.setLocation(this.position.x + (this.velocity.x), this.position.y + (this.velocity.y));
		fixVelocity(isBlocked(this.futureBox));
		updatePhysics();
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
		if(toFix) {
			this.velocity.y *= -.9;
		} else {
			this.velocity.x *= -.9;
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
					return e.hitbox;
				}
			} else if (e instanceof Fuse) {
				if (tf.intersects(e.hitbox)) {
					System.out.println(this.state + " e:" + e.getState());
					if (e.getState() == this.state) {
						LevelController.setCompleted(true);
					}
				}
			}
		}
		return null;
	}

	public Vector2f getVelocity() {
		return this.velocity;
	}

	public boolean getRespawned() {
		return this.respawned;
	}

	public void setRespawned(boolean set) {
		this.respawned = set;
	}

	public void updatePhysics(){
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
	}

	public void setGeom(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
}
