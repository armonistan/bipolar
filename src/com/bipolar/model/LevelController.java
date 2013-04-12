package com.bipolar.model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;
import com.bipolar.entities.Ball;
import com.bipolar.entities.Entity;
import com.bipolar.entities.Player;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.states.Level;
import com.bipolar.view.Camera;

public class LevelController {
	
	private static int levelID;
	private static int worldID;
	private static Scanner s;
	public static Image background, midground, foreground;
	public static Camera camera;
	
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
		EntityController.init(c);
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
		/*try {
			LevelController.populateLevel();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public static void populateLevel() throws SlickException {
		String level = "res/levels/levelObjects.txt";
		
		try {
			s = new Scanner(new File(level));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find level object file.");
		}
		
		if (s != null) {
			while (s.hasNextLine()) {
				//convert teach one to entity and add to entity controller
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
	
	public static void updateLevel() {
		EntityController.update();
		camera.update();
	}
	
	public static void renderLevel(Graphics g) {
		//LevelController.background.draw();
		//LevelController.midground.draw();
		//LevelController.foreground.draw();
		EntityController.render(camera);
	}
	
}
