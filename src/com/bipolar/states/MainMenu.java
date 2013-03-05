package com.bipolar.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.resourceloader.ResourceLoader;

public class MainMenu extends BasicGameState{
	
	public int stateID;
	Input in;
	Image bkg;
	Animation testAnim;
	Sound testSound;
	
	public MainMenu(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		this.bkg = ResourceLoader.resourceHandler.getImage("menubackground");
		this.testAnim = ResourceLoader.resourceHandler.getAnimation("test");
		this.testSound = new Sound("/res/levels/sounds/test.wav");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bkg.draw();
		testAnim.draw(150, 150);
		g.drawString("main menu", container.getWidth()/2, container.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(in.isKeyPressed(Input.KEY_D)){
			game.enterState(Bipolar.INTROSTATE);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
