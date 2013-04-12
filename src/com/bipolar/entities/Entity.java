package com.bipolar.entities;

import java.awt.Point;
import java.awt.Polygon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Entity {
	
	protected int width;
	protected int height;
	
	protected Point position;
	
	protected Image image;
	protected boolean solid, state;
	
	private Polygon quad = new Polygon(); 
	
	public Entity(Image img, Point p){
		image = img;
		
		position = p;
		
		width = img.getWidth();
		height = img.getHeight();
		setPosition(position);
	}
	
	public Entity(int x, int y) {
		Point p = new Point(x, y);
		position = p;
	}
	
	public Entity(int x, int y, boolean solid, boolean state){
		Point p = new Point(x, y);
		this.position = p;
		this.solid = solid;
		this.state = state;
	}
	
	public void setPosition(Point p){
		position = p;
		
		quad.addPoint(position.x, position.y);
		quad.addPoint(position.x+width, position.y);
		quad.addPoint(position.x, position.y+height);
		quad.addPoint(position.x+width, position.y+height);
	}
	
	public void update(){
	}
	
	public void render(Polygon cP){
		for(int i = 0; i<cP.npoints; i++){
			Point p = new Point(quad.xpoints[i], quad.ypoints[i]);
			if(cP.contains(p)){
				image.draw(position.x, position.y);
				return;
			}
		}
	}
	
	public Polygon getPosition(){
		return quad;
	}
}
