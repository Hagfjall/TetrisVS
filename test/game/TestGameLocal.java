package test.game;

import game.Game;
import gui.TetrisGui;
import gui.TetrisPanel;

import java.awt.Color;
import java.net.Socket;

import javax.swing.JFrame;

import network.client.KeyListener;
import network.client.NetworkInputHandler;
import network.client.NetworkOutputHandler;

public class TestGameLocal {

	public static void main(String [] args) {
		new TestGameLocal().run();
	}
	public void run() {
		Game local = new Game(10, 6, 200);
		TestKeyListener keyListener = new TestKeyListener(local, null);
		JFrame f = new JFrame("TetrisVS");
		TetrisPanel tetrisPanel = new TetrisPanel(local);
		f.addKeyListener(keyListener);
		f.add(tetrisPanel);
		f.setBackground(Color.white);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
}
