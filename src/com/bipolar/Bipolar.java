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
import com.bipolar.states.Settings;


public class Bipolar extends StateBasedGame {
	
	public static final int WIDTH			= 800;
	public static final int HEIGHT			= 600;
	public static final int MAXWIDTH		= 5 * WIDTH;
	public static final int MAXHEIGHT		= 5 * HEIGHT;
	
	public static final int LOADINGSTATE	= 0;
	public static final int MENUSTATE		= 1;
	public static final int INTROSTATE		= 2;
	public static final int WORLDSTATE		= 3;
	public static final int SUBWORLDSTATE	= 4;
	public static final int LEVELSTATE		= 5;
	public static final int SETTINGSSTATE	= 6;
	public static final int NUMWORLDS		= 1;
	public static final int LEVELPERWORLD	= 10;
	public static final boolean DEVMODE 	= true;
	public static final int LAYERS = 10;
	
	public static final float G = .0075f;
	public static final float DRAG = .55f;
	
	public static int slowdown = 1;
	public static int currentWorld;
	public static int currentLevel;
	public static boolean intro;
	public static boolean world[];
	public static ArrayList<boolean[]> level;
	
	public static int lastState;
	
	public Bipolar(String name) {
		super(name);
		lastState = 1;
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
		this.addState(new Settings(SETTINGSSTATE));
		this.enterState(LOADINGSTATE);
	}
	
	@Override
	public boolean closeRequested() {
		System.out.println("closing");
		try {
			ResourceHandler.saveGame();
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
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Bipolar("Bipolar"));
		
		app.setMinimumLogicUpdateInterval(2 * slowdown);
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.start();
	}
	
	public static int getLastState(){
		return lastState;
	}
	
	public static void updateState(int lS){
		lastState = lS;
	}
}