package test.gui;

import game.Game;
import gui.StatPanel;

import javax.swing.JFrame;

public class TestScore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestScore().run();
	}

	public void run(){
		JFrame f = new JFrame("TetrisVS");
		Game g = new Game(22, 10);
		StatPanel tetrisPanel = new StatPanel("hejsan",g);
		f.add(tetrisPanel);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tetrisPanel.repaint();
		}
	}
}
