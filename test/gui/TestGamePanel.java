package test.gui;

import game.Game;
import gui.GamePanel;
import gui.InputListener;

import javax.swing.JFrame;

public class TestGamePanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestGamePanel().run();
	}
	
	public void run(){
		JFrame f = new JFrame("TetrisVS");
		Game g = new Game(22,10);
		InputListener input = new InputListener(g);
		GamePanel tetrisPanel = new GamePanel("Hallå", g);
		f.addKeyListener(input);
		f.add(tetrisPanel);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
