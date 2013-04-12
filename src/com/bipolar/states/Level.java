package com.bipolar.states;

import java.awt.Point;
import org.newdawn.slick.Color;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.entities.Ball;
import com.bipolar.model.LevelController;
import com.bipolar.entities.BallSpawner;
import com.bipolar.entities.Entity;
import com.bipolar.entities.Player;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.view.Camera;

public class Level extends BasicGameState {
	
	public int stateID;
	private Camera camera;
	int worldID, levelID;
	Input in;
	
	public Level(int id) {
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.in = container.getInput();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Player temp = new Player(ResourceLoader.getImage("tempball"), new Point(0,0), container.getInput());
		Ball bs = new Ball(ResourceLoader.getImage("tempball"), new Point(10,50));
		entities.add(temp);
		entities.add(bs);
		camera = new Camera(container);
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		this.worldID = Bipolar.currentWorld;
		this.levelID = Bipolar.currentLevel;
		if (Bipolar.DEVMODE) {
			System.out.println("Welcome developer!");
			//TODO set access for certain permissions here, like possible level editing
		} else { 
			//TODO interface with LevelController to load the appropriate level stuff
		}
		LevelController.enter();
		Player.setInput(in);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString(this.worldID + ", " + this.levelID, container.getWidth()/2, container.getHeight()/2);
		camera.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Bipolar.SUBWORLDSTATE);
		}
		camera.update();
		
		if (in.isKeyPressed(Input.KEY_ENTER)) {
			if (this.levelID + 1 < Bipolar.LEVELPERWORLD) {
				Bipolar.level.get(this.worldID)[this.levelID + 1] = true;
			} else if (this.worldID + 1 < Bipolar.NUMWORLDS){
				Bipolar.world[this.worldID + 1] = true;
			}
		}
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
