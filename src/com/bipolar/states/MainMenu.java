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
	MenuButton b;
	
	public MainMenu(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		this.bkg = ResourceLoader.getImage("menubackground");
		this.testSound = ResourceLoader.getSound("testsound");
		this.b = new MenuButton(250, 250, ResourceLoader.getAnimation("gobutton"), in);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bkg.draw();
		b.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		b.update();
		if (b.getClicked()) {
			game.enterState(Bipolar.INTROSTATE);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
