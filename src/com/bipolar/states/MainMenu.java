package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.menuobjects.MenuButton;
import com.bipolar.resourceloader.ResourceLoader;

public class MainMenu extends BasicGameState{
	
	public int stateID;
	Input in;
	Image bkg;
	Sound testSound;
	MenuButton newGame;
	MenuButton loadGame;
	
	public MainMenu(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		this.bkg = ResourceLoader.getImage("menubackground");
		
		this.newGame = new MenuButton(ResourceLoader.getAnimation("newbutton"), in);
		this.loadGame = new MenuButton(ResourceLoader.getAnimation("loadbutton"), in);
		
		this.newGame.setPosition((container.getWidth()/3) -
				(this.newGame.getWidth() / 2), 250);
		this.loadGame.setPosition((2 * container.getWidth()/3) -
				(this.loadGame.getWidth() / 2), 250);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game){
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bkg.draw();
		newGame.render();
		loadGame.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		newGame.update();
		loadGame.update();
		
		if (newGame.getClicked()) {
			game.enterState(Bipolar.INTROSTATE);
		}
		
		if (loadGame.getClicked()) {
			game.enterState(Bipolar.WORLDSTATE);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
