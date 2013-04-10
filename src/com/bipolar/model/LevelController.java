package com.bipolar.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bipolar.Bipolar;
import com.bipolar.controller.EntityController;

public class LevelController {
	
	private static int levelID;
	private static int worldID;
	private static Scanner s;
	public static Image background, midground, foreground;
	
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
	
	public static void init() {
		
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
	
	public static void populateLevel() throws SlickException {
		String level = "res/levels/world" +LevelController.worldID + "/"
				+ LevelController.worldID + "-"
				+ LevelController.levelID + ".txt";
		File levelFile = new File(level);
		
		try {
			s = new Scanner(levelFile);
		} catch (FileNotFoundException e) {
			System.out.println("Creating blank level file " 
				+ LevelController.worldID + "," + LevelController.levelID);
			try {
				levelFile.createNewFile();
				s = new Scanner(levelFile);
			} catch (IOException e1) {
				System.out.println("How did you get here..?");
				e1.printStackTrace();
			}
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
	}
	
	public static void renderLevel(Graphics g) {
		LevelController.background.draw();
		LevelController.midground.draw();
		LevelController.foreground.draw();
		EntityController.render();
	}
	
}
