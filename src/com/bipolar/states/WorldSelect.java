package com.bipolar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bipolar.Bipolar;
import com.bipolar.menuobjects.WorldButton;

public class WorldSelect extends BasicGameState {
	
	public int stateID;
	Input in;
	WorldButton[] worlds;
	
	public WorldSelect(int id) {
		this.stateID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		int row = 0;
		this.in = container.getInput();
		
		worlds = new WorldButton[Bipolar.NUMWORLDS];
		for (int world = 0; world < this.worlds.length; world++) {
			this.worlds[world] = new WorldButton(world, in);
			if (world % 4 == 0) {
				row++;
			}
			this.worlds[world].setPosition(150 * world + 100, row * 100);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for (int world = 0; world < this.worlds.length; world++) {
			this.worlds[world].render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		for (int world = 0; world < this.worlds.length; world++) {
			this.worlds[world].update();
			if (this.worlds[world].getClicked() && Bipolar.world[world]) {
				Bipolar.currentWorld = world;
				System.out.println("Going to world " + world);
				game.enterState(Bipolar.SUBWORLDSTATE);
			}
		}
		
		if (in.isKeyPressed(Input.KEY_A)) {
			game.enterState(Bipolar.MENUSTATE);
		}
		
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
