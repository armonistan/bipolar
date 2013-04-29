package com.bipolar.entities;

import java.util.ArrayList;

public class Wire extends Entity{
	private ArrayList<Entity> connections = null;
	private Entity input;

	public Wire(int xpos, int ypos, boolean solid, int state, int drawLayer) {
		super(xpos, ypos, solid, state, drawLayer);
		this.input = null;
		this.connections = new ArrayList<Entity>();
	}

	public void setInput(Entity in){
		this.input = in;
	}

	public void addConnection(Entity connect) {
		connections.add(connect);
	}

	public void update(){
		if (this.connections != null && this.connections.size() > 0) {
			if (this.input == null) {
				this.input = this.connections.get(0);
				this.connections.remove(0);
			}
			if (this.input.getState() == 1) {
				System.out.println(this.input);
				for (Entity e : this.connections) {
					if(e.cool()) {
						e.activate();
					}
				}
			}
		}
	}

}
