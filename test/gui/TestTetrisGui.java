package test.gui;

import game.Game;
import gui.TetrisGui;

public class TestTetrisGui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestTetrisGui().run();
	}

	public void run() {
		Game local = new Game(22, 10,200);
		Game opponent = new Game(22, 10);
		new TetrisGui("local","opponent",local,opponent);
		
	}

}
