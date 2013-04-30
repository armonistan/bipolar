package com.bipolar.entities;

import org.newdawn.slick.geom.Rectangle;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class Gate extends Entity{
	
	private boolean cool = true;
	private long t1;
	
	public Gate(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		setSpriteSheet(ResourceLoader.getImage("gate"), 16, 156);
		this.hitbox = new Rectangle(xpos, ypos, this.image.getWidth(), this.image.getHeight());
		this.state = state;
		this.cool = true;
		this.activate();
	}
	
	public boolean cool() {
		return cool;
	}
	
	public void activate() {
		t1 = Bipolar.elapsedTime;
		this.cool = false;
		if (this.state == 0) {
			this.setSolid(false);
			this.toggleAnimation();
			this.setState(1);
		} else if (this.state == 1) {
			this.setSolid(true);
			this.image = this.sheet.getSubImage(0, 0);
			this.setState(0);
		}
	}
	
	public void update() {
		if (this.animating) {
			this.anim.stopAt(this.anim.getFrameCount());
			this.toggleAnimation();
		}
		
		if (!this.cool && (Bipolar.elapsedTime - this.t1 > 1000)) {
			this.cool = true;
		}
	}

}
