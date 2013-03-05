package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class LevelSelect extends BasicGameState{
	
	public int stateID;
	Input in;
	
	public LevelSelect(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("level select", container.getWidth()/6, container.getHeight()/10);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(in.isKeyPressed(Input.KEY_D)){
			game.enterState(Bipolar.LEVELSTATE);
		}
		if(in.isKeyPressed(Input.KEY_A)){
			game.enterState(Bipolar.WORLDSTATE);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
