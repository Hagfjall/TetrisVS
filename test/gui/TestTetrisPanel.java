package test.gui;

import game.Game;
import gui.InputListener;
import gui.TetrisPanel;

import javax.swing.JFrame;

public class TestTetrisPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestTetrisPanel().run();
	}
	
	public void run(){
		JFrame f = new JFrame("TetrisVS");
		Game g = new Game(22,10);
		InputListener input = new InputListener(g);
		TetrisPanel tetrisPanel = new TetrisPanel(g);
		f.addKeyListener(input);
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
