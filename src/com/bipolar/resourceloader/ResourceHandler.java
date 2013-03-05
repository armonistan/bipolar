package com.bipolar.resourceloader;

import java.util.TreeMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class ResourceHandler {
	
	public static TreeMap<String, Image> images = new TreeMap<String, Image>();
	public static TreeMap<String, Animation> animations = new TreeMap<String, Animation>();
	public static TreeMap<String, Sound> sounds = new TreeMap<String, Sound>();
	public SpriteSheet sheet;
	public Image img = null;
	public Animation anim = null;
	public Sound sound = null;
	
	public ResourceHandler(){
		
	}
	
	public void parse(String args){
		if (!args.equals(null)) {
			String[] split = args.split("[: ]+");

			if (split[0].equals("image")) {
				loadImage(split[1], split[2]);
			} else if (split[0].equals("animation")) {
				loadAnimation(split[1], split[2], Integer.parseInt(split[3]), 
						Integer.parseInt(split[4]), Integer.parseInt(split[5]));
			} else if (split[0].equals("sound")) {
				loadSound(split[1], split[2]);
			} else {
				System.out.println("Invalid file type: " + split[0]);
			}
		}
	}
	
	public void loadImage(String name, String path){
		try {
			this.img = new Image(path);
			images.put(name, this.img);
		} catch (SlickException e) {
			System.out.println("Could not load image at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public void loadAnimation(String name, String path, int w, int h, int duration){
		try {
			this.img = new Image(path);
			this.sheet = new SpriteSheet(this.img, w, h);
			this.anim = new Animation(this.sheet, duration);
			animations.put(name, this.anim);
		} catch (SlickException e) {
			System.out.println("Could not load animation at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public void loadSound(String name, String path){
		try {
			this.sound = new Sound(path);
			sounds.put(name, this.sound);
		} catch (SlickException e) {
			System.out.println("Could not load sound at " + path + ".");
			e.printStackTrace();
		}
	}
	
	public Image getImage(String name){
		if (!images.ceilingEntry(name).equals(null)) {
			return images.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find image " + name);
		
		return null;
	}
	
	public Animation getAnimation(String name){
		if (!animations.ceilingEntry(name).equals(null)) {
			return animations.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find image " + name);
		
		return null;
	}
	
	public Sound getSound(String name){
		if (!sounds.ceilingEntry(name).equals(null)) {
			return sounds.ceilingEntry(name).getValue();
		}
		System.out.println("Can't find image " + name);
		
		return null;
	}
	
}
