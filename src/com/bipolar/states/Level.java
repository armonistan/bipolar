package com.bipolar.states;

import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.svg.Gradient;

import com.bipolar.Bipolar;
import com.bipolar.model.LevelController;
import com.bipolar.entities.Player;

public class Level extends BasicGameState {
	
	public int stateID;
	public static Graphics drawObj = null;
	int worldID, levelID;
	Input in;
	
	public Level(int id) {
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.in = container.getInput();
		LevelController.init(container);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		this.worldID = Bipolar.currentWorld;
		this.levelID = Bipolar.currentLevel;

		LevelController.clearLevel();
		LevelController.enter();
		Player.setInput(in);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Level.drawObj = g;
		g.setBackground(new Color(0x93CCEA));
		LevelController.renderLevel(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Bipolar.elapsedTime = container.getTime() - Bipolar.startTime;
		if (LevelController.getCompleted()) {
			LevelController.destroyLevel();
			LevelController.setCompleted(false);
			if (this.levelID + 1 < Bipolar.LEVELPERWORLD) {
				Bipolar.level.get(this.worldID)[this.levelID + 1] = true;
			} else if (this.worldID + 1 < Bipolar.NUMWORLDS){
				Bipolar.world[this.worldID + 1] = true;
			}
			game.enterState(Bipolar.SUBWORLDSTATE);
		} else {
			if (in.isKeyPressed(Input.KEY_C)) {
				LevelController.toggleCamera();
			}
			LevelController.updateLevel(delta);
		}
		
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(Bipolar.SUBWORLDSTATE);
			LevelController.destroyLevel();
			LevelController.setCompleted(false);
		}
		container.setMinimumLogicUpdateInterval(2 * Bipolar.slowdown);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
