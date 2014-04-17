package test.gui;

import java.awt.Color;

import game.Game;
import gui.KeyListener;
import gui.TetrisPanel;

import javax.swing.JFrame;

public class TestTetrisPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestTetrisPanel().run();
	}

	public void run() {
		JFrame f = new JFrame("TetrisVS");
		Game g = new Game(22, 10);
		KeyListener input = new KeyListener(g,null);
		TetrisPanel tetrisPanel = new TetrisPanel(g);
		f.addKeyListener(input);
		f.add(tetrisPanel);
		f.setBackground(Color.white);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
