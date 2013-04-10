package com.bipolar.resourceloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.bipolar.Bipolar;

public class ResourceHandler {
	
	public static TreeMap<String, Image> images = new TreeMap<String, Image>();
	public static TreeMap<String, Animation> animations = new TreeMap<String, Animation>();
	public static TreeMap<String, Sound> sounds = new TreeMap<String, Sound>();
	public static SpriteSheet sheet;
	public static Image img = null;
	public static Animation anim = null;
	public static Sound sound = null;
	
	public static void parse(String args) {
		if (!args.equals(null) && args.length() > 1) {
			if (!args.contains("//")) {
				String[] split = args.split("[: ]+");

				String type = split[0];
				String name = split[1];
				String path = split[2];
				int width = 0;
				int height = 0;
				int duration = 0;
				
				System.out.println("loading resource: " + name);
				
				if (split.length > 3) {
					width = Integer.parseInt(split[3]);
					height = Integer.parseInt(split[4]);
					if (split.length > 5) {
						duration = Integer.parseInt(split[5]);
					} else {
						duration = 100;
					}
				}

				if (type.equals("image")) {
					loadImage(name, path);
				} else if (type.equals("animation")) {
					loadAnimation(name, path, width, height, duration);
				} else if (type.equals("sound")) {
					loadSound(name, path);
				} else {
					System.out.println("Invalid file type: " + name);
				}
			}
		}
	}
	
	public static void loadGame(String args) {
		if (!args.equals(null)) {
			String[] split = args.split("[: ]+");
			
			String name = split[0];
			int worldID = 0;
			int levelID = 0;
			boolean completed = false;
			
			if (split.length == 1) {
				
			} else if (split.length < 3){
				completed = Boolean.parseBoolean(split[1]);
			} else if (split.length < 4) {
				worldID = Integer.parseInt(split[1]);
				completed = Boolean.parseBoolean(split[2]);
			} else if (split.length < 5) {
				worldID = Integer.parseInt(split[1]);
				levelID = Integer.parseInt(split[2]);
				completed = Boolean.parseBoolean(split[3]);
			}
			
			
			if (name.equals("intro")) {
				Bipolar.intro = completed;
			} else if (name.equals("world") && worldID < Bipolar.NUMWORLDS) {
				Bipolar.world[worldID] = completed;
			} else if (name.equals("level") 
					&& levelID < Bipolar.LEVELPERWORLD && worldID < Bipolar.NUMWORLDS) {
				Bipolar.level.get(worldID)[levelID] = completed;
			}
		}
	}
	
	public static void saveGame() throws IOException {
		FileWriter fw = new FileWriter((new File("res/sav.txt").getAbsoluteFile()));
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("intro:" + Bipolar.intro + "\n");
		
		for (int world = 0; world < Bipolar.world.length; world++) {
			bw.write("world:" + world + ":" + Bipolar.world[world] +"\n");
			for (int level = 0; level < Bipolar.level.get(world).length; level++) {
				bw.write("level:" + world + ":" + level + ":"
						+ Bipolar.level.get(world)[level] + "\n");
			}
		}
		
		bw.close();
	}
	
	public static void loadImage(String name, String path) {
		try {
			ResourceHandler.img = new Image(path);
			images.put(name, ResourceHandler.img);
		} catch (SlickException e) {
			System.out.println("Could not load image at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public static void loadAnimation(String name, String path, int w, int h, int duration) {
		try {
			ResourceHandler.img = new Image(path);
			ResourceHandler.sheet = new SpriteSheet(ResourceHandler.img, w, h);
			ResourceHandler.anim = new Animation(ResourceHandler.sheet, duration);
			animations.put(name, ResourceHandler.anim);
		} catch (SlickException e) {
			System.out.println("Could not load animation at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public static void loadSound(String name, String path) {
		try {
			ResourceHandler.sound = new Sound(path);
			sounds.put(name, ResourceHandler.sound);
		} catch (SlickException e) {
			System.out.println("Could not load sound at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public static Image getImage(String name) {
		if (!images.ceilingEntry(name).equals(null)) {
			return images.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find image " + name);
		
		return null;
	}
	
	public static Animation getAnimation(String name) {
		if (!animations.ceilingEntry(name).equals(null)) {
			return animations.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find animation " + name);
		
		return null;
	}
	
	public static Sound getSound(String name) {
		if (!sounds.ceilingEntry(name).equals(null)) {
			return sounds.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find sound " + name);
		
		return null;
	}
	
}
