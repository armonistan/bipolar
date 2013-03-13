package com.bipolar;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.resourceloader.ResourceHandler;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.states.Intro;
import com.bipolar.states.Level;
import com.bipolar.states.LevelSelect;
import com.bipolar.states.MainMenu;
import com.bipolar.states.WorldSelect;


public class Bipolar extends StateBasedGame{
	
	public static final int WIDTH			= 800;
	public static final int HEIGHT			= 600;
	
	public static final int LOADINGSTATE	= 0;
	public static final int MENUSTATE		= 1;
	public static final int INTROSTATE		= 2;
	public static final int WORLDSTATE		= 3;
	public static final int SUBWORLDSTATE	= 4;
	public static final int LEVELSTATE		= 5;
	public static final int NUMWORLDS		= 2;
	public static final int LEVELPERWORLD	= 10;
	
	public static int currentWorld;
	public static int currentLevel;
	public static boolean intro;
	public static boolean world[];
	public static ArrayList<boolean[]> level;
	
	public static ResourceHandler resources = ResourceLoader.resourceHandler;
	
	public Bipolar(String name) {
		super(name);
		
		world = new boolean[NUMWORLDS];
		level = new ArrayList<boolean[]>();
		for (int i = 0; i < NUMWORLDS; i++) {
			level.add(new boolean[LEVELPERWORLD]);
		}
		
		this.addState(new ResourceLoader(LOADINGSTATE));
		this.addState(new MainMenu(MENUSTATE));
		this.addState(new Intro(INTROSTATE));
		this.addState(new WorldSelect(WORLDSTATE));
		this.addState(new LevelSelect(SUBWORLDSTATE));
		this.addState(new Level(LEVELSTATE));
		this.enterState(LOADINGSTATE);
	}
	
	@Override
	public boolean closeRequested()
	{
		System.out.println("closing");
		try {
			resources.saveGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
		return false;
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(LOADINGSTATE).init(container, this);
		this.getState(MENUSTATE).init(container, this);
		this.getState(INTROSTATE).init(container, this);
		this.getState(WORLDSTATE).init(container, this);
		this.getState(SUBWORLDSTATE).init(container, this);
		this.getState(LEVELSTATE).init(container, this);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Bipolar("Bipolar"));
		
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.start();
	}
}