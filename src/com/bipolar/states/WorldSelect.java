package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;

public class WorldSelect extends BasicGameState{
	
	public int stateID;
	Input in;
	
	public WorldSelect(int id){
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
		g.drawString("world select", container.getWidth()/3, container.getHeight()/3);
		for(int i = 0; i < Bipolar.level.size(); i++){
			g.drawString(Bipolar.level.get(i).toString(), 100, 100 + 15 * i);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(in.isKeyPressed(Input.KEY_D)){
			game.enterState(Bipolar.SUBWORLDSTATE);
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
