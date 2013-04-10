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

public class Settings extends BasicGameState{
	
	public int stateID;
	
	Input in;
	Image bkg;
	MenuButton upButton;
	MenuButton downButton;
	MenuButton applyButton;
	MenuButton goBackButton;
	
	int testVariable;
	int tempTestVariable;
	
	public Settings(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		this.bkg = ResourceLoader.getImage("settingsbackground");
		
		this.upButton = new MenuButton(ResourceLoader.getAnimation("upbutton"), in);
		this.downButton = new MenuButton(ResourceLoader.getAnimation("downbutton"), in);
		this.applyButton = new MenuButton(ResourceLoader.getAnimation("applyButton"), in);
		this.goBackButton = new MenuButton(ResourceLoader.getAnimation("backbutton"), in);
		
		this.upButton.setPosition(300, 150);
		this.downButton.setPosition(300, 250);
		this.applyButton.setPosition(300, 350);
		this.goBackButton.setPosition(300, 450);
		
		testVariable = 0;
		tempTestVariable = 0;
	}	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bkg.draw();
		upButton.render();
		downButton.render();
		applyButton.render();
		goBackButton.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		applyButton.update();
		goBackButton.update();
		upButton.update();
		downButton.update();
		
		if (applyButton.getClicked()) {
			this.applyChanges();
		}
		
		if (goBackButton.getClicked()) {
			game.enterState(Bipolar.getLastState());
		}
		
		if (upButton.getClicked()){
			this.incrementVariable();
		}

		if (downButton.getClicked()){
			this.decrementVariable();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}
	
	private void incrementVariable(){
		tempTestVariable++;
		System.out.println(tempTestVariable);
	}
	
	private void decrementVariable(){
		tempTestVariable--;
		System.out.println(tempTestVariable);
	}
	
	private void applyChanges(){
		testVariable = tempTestVariable;
		System.out.println(testVariable);
	}
}