package test.gui;

import game.Game;
import gui.InputListener;
import gui.TetrisGui;
import gui.TetrisPanel;

import javax.swing.JFrame;

public class TestTetrisGui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestTetrisGui().run();
	}

	public void run() {
		Game local = new Game(22, 10);
		Game opponent = new Game(22, 10);
		TetrisGui f = new TetrisGui("local","opponent",local,opponent);
		InputListener input = new InputListener(local);
		f.addKeyListener(input);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
