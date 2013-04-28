package com.bipolar.entities;

import org.newdawn.slick.geom.Vector2f;
import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.view.Camera;

public class Field extends Entity{

	private int charge;

	public Field(int xpos, int ypos, int state, int charge) {
		super(xpos, ypos, charge);
		this.charge = charge;
		this.state = state;
		if (state == 0) {
			if (charge < 0) {
				this.image = ResourceLoader.getImage("negmag");
			} else {
				this.image = ResourceLoader.getImage("posmag");
			}
		} else {
			if (charge < 0) {
				this.image = ResourceLoader.getImage("negelec");
			} else {
				this.image = ResourceLoader.getImage("poselec");
			}
		}
	}

	public Vector2f getInfluence(Vector2f pos) {
		Vector2f forceExerted;

		float dist = this.position.distanceSquared(pos);
		forceExerted = new Vector2f();
		forceExerted.set(pos.sub(this.position).normalise());
		float force = (float) (Bipolar.FORCE_SCALE * (this.charge * Bipolar.PERMEABILITY)/(4 * dist));
		forceExerted.scale(force);
		return forceExerted;
	}
	
	@Override
	public void transform(Camera c) {
		float transformedX = (position.x - this.image.getWidth() / 2) - c.getCameraPort().getMinX();
		float transformedY = (position.y - this.image.getHeight() / 2) - c.getCameraPort().getMinY();

		transformedPosition.set(transformedX, transformedY);
	}
}
