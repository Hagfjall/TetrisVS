package test.game;

import game.Game;
import game.TetrisTimer;
import gui.TetrisGui;
import gui.TetrisPanel;

import java.awt.Color;

import javax.swing.JFrame;

public class TestGameLocal {

	public static void main(String[] args) {
		new TestGameLocal().run();
	}

	public void run() {
		Game local = new Game(22, 10, 200);
		Game opponent = new Game(22, 10, 200);
		TestKeyListener keyListener = new TestKeyListener(local);
		new TetrisTimer(keyListener, 750);
		new TetrisGui("testlocal", "testOpponent", local, opponent, keyListener);
	}

}
