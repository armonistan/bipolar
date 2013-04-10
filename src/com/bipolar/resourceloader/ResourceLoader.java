package com.bipolar.resourceloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class ResourceLoader extends BasicGameState {
	
	public int stateID;
	public String loadString;
	boolean finishedLoading;
	Scanner s;
	
	public ResourceLoader(int id) {
		this.stateID = id;
	}
	
	public static Image getImage(String name) {
		return ResourceHandler.getImage(name.toLowerCase()).copy();
	}
	
	public static Animation getAnimation(String name) {
		return ResourceHandler.getAnimation(name.toLowerCase()).copy();
	}
	
	public static Sound getSound(String name) {
		return ResourceHandler.getSound(name.toLowerCase());
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		try {
			s = new Scanner(new File("res/load.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find load file");
			e.printStackTrace();
		}
		
		finishedLoading = false;
		loadString = "loading resources...";
		while (s.hasNextLine()) {
			ResourceHandler.parse(s.nextLine());
		}
		
		try {
			s = new Scanner(new File("res/sav.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find load file");
			e.printStackTrace();
		}
		while (s.hasNextLine()) {
			ResourceHandler.loadGame(s.nextLine());
		}
		
		finishedLoading = true;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO ADD LOADING BAR
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO RENDER LOADING BAR AND LOADING BACKGROUND
		System.out.println(loadString);
		if (finishedLoading) {
			game.enterState(Bipolar.MENUSTATE);
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
}
