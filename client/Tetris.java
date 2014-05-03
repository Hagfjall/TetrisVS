package client;

import game.Game;
import gui.TetrisGui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Tetris {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String name = JOptionPane.showInputDialog("Please enter your name");
		if (name == null)
			return;
		// String address = JOptionPane
		// .showInputDialog("Please enter the server address");
		// int port = Integer.parseInt(JOptionPane
		// .showInputDialog("Please enter the portnumber"));
		String address = "localhost";
		int port = 3000;
		Socket s = new Socket(address, port);
		InitiateConnectionClient init = new InitiateConnectionClient(s, name);
		long rndSeed = init.getRndSeed();
		String opponentName = init.getOpponentName();
		NetworkOutputHandler nout = new NetworkOutputHandler(s);
		Game local = new Game(22, 10, rndSeed, nout);
		Game opponent = new Game(22, 10, rndSeed);
		NetworkInputHandler nin = new NetworkInputHandler(s, opponent);
		nin.start();
		KeyListener keyListener = new KeyListener(local, nout);
		new TetrisGui(name, opponentName, local, opponent, keyListener);

	}

}
