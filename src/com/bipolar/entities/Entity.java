package com.bipolar.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.bipolar.Bipolar;
import com.bipolar.model.LevelController;
import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class Entity {

	protected int width;
	protected int height;
	protected int state;
	protected int drawLayer;

	protected Vector2f position;
	protected Vector2f transformedPosition;

	protected Image image;
	protected SpriteSheet sheet;
	protected Animation anim;
	protected boolean solid;
	protected boolean animating;
	protected int currentFrame;
	protected int sheetWidth;
	protected int cycles;

	protected Rectangle hitbox;
	protected Rectangle tfbox;

	public Entity(int x, int y) {
		this.position = new Vector2f(x,y);
		this.transformedPosition = new Vector2f(x,y);
		this.image = null;
		this.width = 0;
		this.height = 0;
		this.currentFrame = 0;
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
		this.currentFrame = 0;
	}

	public Entity(int x, int y, int charge) {
		this.position = new Vector2f(x,y);
		this.transformedPosition = new Vector2f(x,y);
		this.image = null;
		this.width = 0;
		this.height = 0;
		this.currentFrame = 0;
	}

	public void update() {
	}

	public void setSpriteSheet(Image img, int w, int h) {
		this.sheet = new SpriteSheet(img, w, h);
		this.image = this.sheet.getSubImage(0, 0);
		this.sheetWidth = this.sheet.getHorizontalCount() - 1;
		this.anim = new Animation(this.sheet, 200);
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

	public void toggleAnimation() {
		if (this.animating) {
			this.image = this.sheet.getSubImage(this.sheetWidth, 0);
			this.anim.stop();
			this.animating = false;
		} else {
			this.anim.start();
			this.animating = true;
		}
	}

	public void animate() {
		if (this.anim != null) {
			this.image = null;
			Level.drawObj.drawAnimation(this.anim, this.transformedPosition.x, this.transformedPosition.y);
		}
	}

	public void render(Camera c) {
		transform(c);
		if (this.animating) {
			this.animate();
		}
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
