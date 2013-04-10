package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.menuobjects.MenuButton;
import com.bipolar.resourceloader.ResourceLoader;

public class MainMenu extends BasicGameState {
	
	public int stateID;
	Input in;
	Image bkg;
	MenuButton newGame;
	MenuButton loadGame;
	MenuButton settings;
	
	public MainMenu(int id) {
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.in = container.getInput();
		this.bkg = ResourceLoader.getImage("menubackground");
		
		this.newGame = new MenuButton(ResourceLoader.getAnimation("newbutton"), in);
		this.loadGame = new MenuButton(ResourceLoader.getAnimation("loadbutton"), in);
		this.settings = new MenuButton(ResourceLoader.getAnimation("gobutton"), in);
		
		this.newGame.setPosition((container.getWidth()/3) -
				(this.newGame.getWidth() / 2), 250);
		this.loadGame.setPosition((2 * container.getWidth()/3) -
				(this.loadGame.getWidth() / 2), 250);
		this.settings.setPosition((2 * container.getWidth()/4) -
				(this.settings.getWidth() / 2), 250);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bkg.draw();
		newGame.render(g);
		loadGame.render(g);
		settings.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		newGame.update();
		loadGame.update();
		settings.update();
		Bipolar.updateState(Bipolar.MENUSTATE);
		
		
		if (newGame.getClicked() && !Bipolar.intro) {
			Bipolar.intro = true;
			Bipolar.world[0] = true;
			Bipolar.level.get(0)[0] = true;
			game.enterState(Bipolar.INTROSTATE);
		}
		
		if (loadGame.getClicked() && Bipolar.intro) {
			game.enterState(Bipolar.WORLDSTATE);
		}
		
		if (settings.getClicked()){
			game.enterState(Bipolar.SETTINGSSTATE);
		}
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
