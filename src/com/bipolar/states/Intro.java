package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class Intro extends BasicGameState{
	
	public int stateID;
	Input in;
	
	public Intro(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.in = container.getInput();
		Bipolar.world[0] = true;
		Bipolar.level.get(0)[0] = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Temporary intro scene", container.getWidth()/2, container.getHeight()/4);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(in.isKeyPressed(Input.KEY_D)){
			game.enterState(Bipolar.WORLDSTATE);
		}
		if(in.isKeyPressed(Input.KEY_A)){
			game.enterState(Bipolar.MENUSTATE);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
