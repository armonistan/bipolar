package com.bipolar.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.entities.BallSpawner;
import com.bipolar.entities.Bar;
import com.bipolar.entities.Field;
import com.bipolar.entities.Fuse;
import com.bipolar.entities.Gate;
import com.bipolar.entities.Machine;
import com.bipolar.entities.Platform;
import com.bipolar.entities.Player;
import com.bipolar.entities.PlayerSpawner;
import com.bipolar.view.Camera;
import com.bipolar.entities.Pad;
import com.bipolar.entities.Sparks;
import com.bipolar.entities.Sphere;
import com.bipolar.entities.Steam;
import com.bipolar.entities.Wire;

public class LevelController {
	
	private static int levelID;
	private static int worldID;
	private static Scanner s;
	public static Image background, midground, foreground;
	public static Camera camera;
	public static int delta;
	
	public static void setLevel(int level) {
		if (level > -1 && level < Bipolar.LEVELPERWORLD) {
			LevelController.levelID = level;
		}
	}
	
	public static void setWorld(int world) {
		if (world > -1 && world < Bipolar.NUMWORLDS) {
			LevelController.worldID = world;
		}
	}
	
	public static void init(GameContainer c) {
		camera = new Camera(c);
	}
	
	public static void enter() {
		/*
		 * This sets everything for the level:
		 * populate
		 * setbkg
		 * setmdg
		 * setfrg
		 * 
		 */
		LevelController.setLevel(Bipolar.currentLevel);
		LevelController.setWorld(Bipolar.currentWorld);
		try {
			LevelController.populateLevel();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void clearLevel(){
		EntityController.deleteAllEntities();
	}
	
	public static void populateLevel() throws SlickException {
		String level = "res/levels/levelObjects.txt";
		try {
			s = new Scanner(new File(level));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find level object file.");
		}
		
		if (s != null) {
			boolean inLevel = false;
			
			while (s.hasNextLine()) {
				String parsee = s.nextLine();
				parsee = parsee.toLowerCase();
				if (parsee.startsWith("#")) {
					String[] split = parsee.split("[: ]+");
					if((Integer.parseInt(split[1]) 
							== LevelController.worldID)
							&& (Integer.parseInt(split[2])
							== LevelController.levelID)) {
						inLevel = !inLevel;
					}
				} else if (inLevel && !(parsee.length() < 2) 
						&& !(parsee.startsWith("//"))) {
					LevelController.parse(parsee);
				}
			}
		}
	}
	
	public static void parse(String args) {
		if (!args.equals(null) && args.length() > 1) {
				String[] split = args.split("[: ]+");

				String type = split[0];
				int xpos = Integer.parseInt(split[1]);
				int ypos = Integer.parseInt(split[2]);
				int xsize = Integer.parseInt(split[3]);
				int ysize = Integer.parseInt(split[4]);
				boolean solid = Boolean.parseBoolean(split[3]);
				int state = Integer.parseInt(split[4]);
				
				System.out.println("Adding object: " + type + " to level.");
				
				if (type.equals("player")) {
					Player temp = new Player(xpos, ypos);
					EntityController.setPlayer(temp);
					EntityController.addEntity(temp);
				} else if (type.equals("platform")) {
					EntityController.addEntity(new Platform(xpos, ypos, xsize, ysize));
				} else if (type.equals("ballspawner")) {
					EntityController.addEntity(new BallSpawner(xpos, ypos));
				} else if (type.equals("playerspawner")) {
					EntityController.addEntity(new PlayerSpawner(xpos, ypos));
				} else if (type.equals("bar")) {
					EntityController.addEntity(new Bar(xpos, ypos, solid, state));
				} else if (type.equals("field")) {
					EntityController.addEntity(new Field(xpos, ypos, solid, state));
				} else if (type.equals("fuse")) {
					EntityController.addEntity(new Fuse(xpos, ypos, solid, state));
				} else if (type.equals("gate")) {
					EntityController.addEntity(new Gate(xpos, ypos, solid, state));
				} else if (type.equals("machine")) {
					EntityController.addEntity(new Machine(xpos, ypos, solid, state));
				} else if (type.equals("pad")) {
					EntityController.addEntity(new Pad(xpos, ypos, solid, state));
				} else if (type.equals("sparks")) {
					EntityController.addEntity(new Sparks(xpos, ypos, solid, state));
				} else if (type.equals("sphere")) {
					EntityController.addEntity(new Sphere(xpos, ypos, solid, state));
				} else if (type.equals("steam")) {
					EntityController.addEntity(new Steam(xpos, ypos, solid, state));
				} else if (type.equals("wire")) {
					EntityController.addEntity(new Wire(xpos, ypos, solid, state));
				}
		}
	}
	
	public static void destroyLevel() {
		EntityController.deleteAllEntities();
	}
	
	public static void setBackground(Image bkg) {
		LevelController.background = bkg;
	}
	
	public static void setMidground(Image mdg) {
		LevelController.midground = mdg;
	}
	
	public static void setForeground(Image frg) {
		LevelController.foreground = frg;
	}
	
	public static void updateLevel(int delta) {
		EntityController.update();
		LevelController.delta = delta;
		LevelController.camera.update();
	}
	
	public static void renderLevel(Graphics g) {
		//LevelController.background.draw();
		//LevelController.midground.draw();
		//LevelController.foreground.draw();
		EntityController.render(camera);
	}
	
}
