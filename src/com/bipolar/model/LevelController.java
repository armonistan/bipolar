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

	private static boolean connect = false;
	private static Wire w = null;
	private static boolean completed = false;
	private static int numFuses = 0;
	private static int currentFuses = 0;
	private static boolean cameraMode = false;

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
		EntityController.enter();
		camera.enter();
	}

	public static void clearLevel(){
		EntityController.deleteAllEntities();
		LevelController.numFuses = 0;
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
			int xsize = 0;
			int ysize = 0;
			boolean solid = false;
			int state = -1;
			if (split[3].equals("true") || split[3].equals("false")) {
				solid = Boolean.parseBoolean(split[3]);
				state = Integer.parseInt(split[4]);
			} else {
				xsize = Integer.parseInt(split[3]);
				ysize = Integer.parseInt(split[4]);
			}
			int drawLayer = Integer.parseInt(split[5]);

			System.out.println("Adding object: " + type + " to level.");

			if (type.equals("player")) {
				Player temp = new Player(xpos, ypos, drawLayer);
				EntityController.setPlayer(temp);
				EntityController.addEntity(temp);
			} else if (type.equals("platform")) {
				EntityController.addEntity(new Platform(xpos, ypos, xsize, ysize, drawLayer));
			} else if (type.equals("ballspawner")) {
				BallSpawner bs = new BallSpawner(xpos, ypos, drawLayer);
				EntityController.setBallSpawner(bs);
				EntityController.addEntity(bs);
				EntityController.ballSpawner.spawnBall();
			} else if (type.equals("playerspawner")) {
				PlayerSpawner ps = new PlayerSpawner(xpos, ypos, drawLayer);
				EntityController.setPlayerSpawner(ps);
				EntityController.addEntity(ps);
			} else if (type.equals("bar")) {
				EntityController.addEntity(new Bar(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("fuse")) {
				LevelController.numFuses++;
				EntityController.addEntity(new Fuse(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("gate")) {
				Gate g = new Gate(xpos, ypos, solid, state, drawLayer);
				EntityController.addEntity(g);
				if (connect && w != null) {
					w.addConnection(g);
				}
			} else if (type.equals("machine")) {
				EntityController.addEntity(new Machine(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("pad")) {
				Pad p = new Pad(xpos, ypos, solid, state, drawLayer);
				EntityController.addEntity(p);
				if (connect && w != null) {
					System.out.println(w + " " + connect);
					w.addConnection(p);
				}
			} else if (type.equals("sparks")) {
				EntityController.addEntity(new Sparks(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("sphere")) {
				EntityController.addEntity(new Sphere(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("steam")) {
				EntityController.addEntity(new Steam(xpos, ypos, solid, state, drawLayer));
			} else if (type.equals("wire")) {
				w = new Wire(xpos, ypos, solid, state, drawLayer);
				connect = !connect;
				System.out.println(w + " " + connect);
				EntityController.addEntity(w);
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
	
	public static void toggleCamera() {
		if (LevelController.cameraMode) {
			LevelController.camera.snapToPlayer();
		} else {
			LevelController.camera.snapToBall();
		}
		LevelController.cameraMode = !LevelController.cameraMode;
	}
	
	public static boolean getCameraFocus() {
		return LevelController.cameraMode;
	}

	public static boolean getCompleted() {
		return LevelController.completed;
	}

	public static void addPowered() {
		LevelController.currentFuses++;
		System.out.println(LevelController.currentFuses + " of " + LevelController.numFuses);
		if (LevelController.numFuses == LevelController.currentFuses) {
			LevelController.setCompleted(true);
		}
	}

	public static void setCompleted(boolean complete) {
		LevelController.completed = complete;
	}

	public static void updateLevel(int delta) {
		if (!LevelController.completed) {
			EntityController.update();
			LevelController.delta = delta;
			LevelController.camera.update(LevelController.cameraMode);
		}
	}

	public static void renderLevel(Graphics g) {
		if (!LevelController.completed) {
			//LevelController.background.draw();
			//LevelController.midground.draw();
			//LevelController.foreground.draw();
			EntityController.render(camera);
		}
	}

}
