package com.bipolar.entities;

import java.awt.Point;
import java.awt.Polygon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.bipolar.view.Camera;
import com.bipolar.resourceloader.ResourceLoader;

public class Entity {
	
	protected int width;
	protected int height;
	
	protected Point position;
	protected Point transformedPosition;
	
	protected Image image;
	protected boolean solid, state;
	
	private Polygon quad = new Polygon();
	private Polygon transformedQuad = new Polygon();
	
	public Entity(Image img, Point p){
		image = img;
		
		position = p;
		transformedPosition = p;
		
		width = img.getWidth();
		height = img.getHeight();
		setPosition(position);
	}
	
	public Entity(int x, int y) {
		Point p = new Point(x, y);
		this.position = p;
		this.image = ResourceLoader.getImage("tempballspawner");
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
	
	public void setTransformedPosition(Point p){
		Point transformedPosition = p;
		
		transformedQuad.addPoint(transformedPosition.x, transformedPosition.y);
		transformedQuad.addPoint(transformedPosition.x+width, transformedPosition.y);
		transformedQuad.addPoint(transformedPosition.x, transformedPosition.y+height);
		transformedQuad.addPoint(transformedPosition.x+width, transformedPosition.y+height);
	}
	
	public void update(){
	}
	
	public void render(Camera c){
		transform(c);
		for(int i = 0; i<c.getCameraPort().npoints; i++){
			Point p = new Point(transformedQuad.xpoints[i], transformedQuad.ypoints[i]);
			if(cP.contains(p) && !image.equals(null)){
			
			if(c.getCameraPort().contains(p)){
				image.draw(transformedPosition.x, transformedPosition.x);
				return;
			}
		}
	}
	
	public void transform(Camera c){
		
		int transformedX = position.x - c.getCameraPort().xpoints[0];
		int transformedY = position.y - c.getCameraPort().ypoints[0];
	
		transformedPosition = new Point(transformedX, transformedY);
		System.out.println("check: " + transformedX + "," + transformedY);
		
		setTransformedPosition(transformedPosition);
	}
	
	public Polygon getPosition(){
		setPosition(position);
		return quad;
	}
}