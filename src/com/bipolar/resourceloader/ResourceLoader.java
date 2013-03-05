package com.bipolar.resourceloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class ResourceLoader extends BasicGameState{
	
	public int stateID;
	public static ResourceHandler resourceHandler = new ResourceHandler();
	boolean finishedLoading;
	Scanner s;
	
	public ResourceLoader(int id){
		this.stateID = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO ADD ALL OF THE RESOURCE LOADING DECLARATIONS HERE
		try {
			s = new Scanner(new File("res/load.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find load file");
			e.printStackTrace();
		}
		
		finishedLoading = false;
		while(s.hasNextLine()){
			resourceHandler.parse(s.nextLine());
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
		System.out.println("loading...");
		if(finishedLoading){
			game.enterState(Bipolar.MENUSTATE);
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
}
