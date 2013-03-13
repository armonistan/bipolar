package com.bipolar.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.menuobjects.LevelButton;
import com.bipolar.resourceloader.ResourceLoader;

public class LevelSelect extends BasicGameState{
	
	public int stateID;
	public int worldID;
	Input in;
	
	LevelButton[] levels;
	Animation[] buttonImage;
	
	public LevelSelect(int id){
		this.stateID = id;
		this.worldID = 0;
	}
	
	public void setWorld(int id){
		this.worldID = id;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game){
		this.worldID = Bipolar.currentWorld;
		for (int i = 0; i < levels.length; i++){
			levels[i].setWorld(this.worldID);
		}
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		
		this.levels = new LevelButton[Bipolar.LEVELPERWORLD];
		for (int i = 0; i < levels.length; i++) {
			levels[i] = new LevelButton(worldID, i, in);
			if ( i < 4) {
				levels[i].setPosition((i % 4) * 150 + 100, 100);
			} else if (i < 8) {
				levels[i].setPosition((i % 4) * 150 + 100, 200);
			} else {
				levels[i].setPosition((i % 4) * 150 + 100, 300);
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
		for (int level = 0; level < this.levels.length; level++) {
			this.levels[level].render(g);
		}
		g.drawString("level select", container.getWidth()/6, container.getHeight()/10);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		for (int level = 0; level < this.levels.length; level++) {
			this.levels[level].update();
			if (this.levels[level].getClicked()) {
				Bipolar.currentLevel = level;
				System.out.println("Going to level " + level);
			}
		}
		
		// TODO Auto-generated method stub
		if (in.isKeyPressed(Input.KEY_D)) {
			game.enterState(Bipolar.LEVELSTATE);
		}
		if (in.isKeyPressed(Input.KEY_A)) {
			game.enterState(Bipolar.WORLDSTATE);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
