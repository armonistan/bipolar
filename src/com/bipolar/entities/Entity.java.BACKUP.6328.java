package com.bipolar.entities;

import java.awt.Point;
import java.awt.Polygon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.bipolar.view.Camera;
import com.bipolar.controller.EntityController;
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
	
	public Entity(int x, int y) {
		position = new Point(x,y);
		transformedPosition = new Point(x,y);
		
		this.image = ResourceLoader.getImage("tempballspawner");
		
		width = image.getWidth();
		height = image.getHeight();
		setPosition(position);
	}
	
	public Entity(int x, int y, boolean solid, boolean state){
		Point p = new Point(x, y);
		this.position = p;
		this.solid = solid;
		this.state = state;
	}
	
	public void setPosition(Point p){
		position = p;
		quad.reset();
		
		quad.addPoint(position.x, position.y);
		quad.addPoint(position.x+width, position.y);
		quad.addPoint(position.x, position.y+height);
		quad.addPoint(position.x+width, position.y+height);
	}
	
	public void setTransformedPosition(Point p){
		transformedPosition = p;
		transformedQuad.reset();
		
		transformedQuad.addPoint(transformedPosition.x, transformedPosition.y);
		transformedQuad.addPoint(transformedPosition.x+width, transformedPosition.y);
		transformedQuad.addPoint(transformedPosition.x, transformedPosition.y+height);
		transformedQuad.addPoint(transformedPosition.x+width, transformedPosition.y+height);
	}
	
	public void update(){
	}
	
	public void render(Camera c){
		transform(c);
<<<<<<< HEAD
		for(int i = 0; i<c.getCameraPort().npoints; i++){
			Point p = new Point(transformedQuad.xpoints[i], transformedQuad.ypoints[i]);
			if(c.getCameraPort().contains(p) && !image.equals(null)){
=======
		if(!image.equals(null)){
				System.out.println("drawn");
>>>>>>> 67b437e581f08a6793f31bbeeff7c5b61db540ec
				image.draw(transformedPosition.x, transformedPosition.y);
				return;
		}
	}
	
	public void transform(Camera c){
		int transformedX = position.x - c.getCameraPort().xpoints[0];
		int transformedY = position.y - c.getCameraPort().ypoints[0];
	
		transformedPosition = new Point(transformedX, transformedY);
		
		setTransformedPosition(transformedPosition);
	}
	
	public Polygon getPosition(){
		setPosition(position);
		
		return quad;
	}
}
