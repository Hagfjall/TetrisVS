package test.game;

import game.Game;
import game.TetrisTimer;
import gui.TetrisGui;

public class TestGameLocal {

	public static void main(String[] args) {
		new TestGameLocal().run();
	}

	public void run() {
		Game local = new Game(12, 10, 2110, 100);
		Game opponent = new Game(12, 10, 200, 200);
		TestKeyListener keyListener = new TestKeyListener(local);
		new TetrisTimer(keyListener, 350); 
		new TetrisGui("testlocal", "testOpponent", local, opponent, keyListener);
	}
}
