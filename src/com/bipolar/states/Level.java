package com.bipolar.states;

import java.awt.Point;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.entities.Ball;
import com.bipolar.entities.BallSpawner;
import com.bipolar.entities.Entity;
import com.bipolar.entities.Player;
import com.bipolar.resourceloader.ResourceLoader;
import com.bipolar.view.Camera;

public class Level extends BasicGameState{
	
	public int stateID;
	private Camera camera;
	Input in;
	
	public Level(int id){
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		this.in = container.getInput();
		HashSet<Entity> entities = new HashSet<Entity>();
		Player temp = new Player(ResourceLoader.getImage("tempball"), new Point(-1000,-1100), container.getInput());
		Ball bs = new Ball(ResourceLoader.getImage("tempball"), new Point(10,50));
		entities.add(temp);
		entities.add(bs);
		camera = new Camera(entities, container);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("level", container.getWidth()/4, container.getHeight()/2);
		camera.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			game.enterState(Bipolar.SUBWORLDSTATE);
		}
		camera.update();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
