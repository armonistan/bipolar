package com.bipolar.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class Level extends BasicGameState {
	
	public int stateID;
	int worldID, levelID;
	Input in;
	
	public Level(int id) {
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.in = container.getInput();
		
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
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString(this.worldID + ", " + this.levelID, container.getWidth()/2, container.getHeight()/2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (in.isKeyPressed(Input.KEY_A)) {
			game.enterState(Bipolar.SUBWORLDSTATE);
		}
		
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
