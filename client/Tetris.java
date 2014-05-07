package client;

import game.Game;
import gui.TetrisGui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import network.client.InitiateConnectionClient;
import network.client.KeyListener;
import network.client.NetworkInputHandler;
import network.client.NetworkOutputHandler;

public class Tetris {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String name = JOptionPane.showInputDialog("Please enter your name");
		if (name == null)
			return;
<<<<<<< HEAD
//		 String address = JOptionPane
//		 .showInputDialog("Please enter the server address");
//		 int port = Integer.parseInt(JOptionPane
//		 .showInputDialog("Please enter the portnumber"));
		 String address = "localhost"; //"31.208.39.174";
		 int port = 3000;
=======
		// String address = JOptionPane
		// .showInputDialog("Please enter the server address");
		// int port = Integer.parseInt(JOptionPane
		// .showInputDialog("Please enter the portnumber"));
		String address = "localhost"; // "31.208.39.174";
		int port = 3000;
>>>>>>> 25fc534c3fef02ea3bacde111e412a1d4df507de
		Socket s = new Socket(address, port);
		InitiateConnectionClient init = new InitiateConnectionClient(s, name);
		long rndSeed = init.getRndSeed();
		String opponentName = init.getOpponentName();
		NetworkOutputHandler nout = new NetworkOutputHandler(s);
		Game local = new Game(22, 10, rndSeed, nout);
		Game opponent = new Game(22, 10, rndSeed);
		new NetworkInputHandler(s, opponent).start();
		KeyListener keyListener = new KeyListener(local, nout);
		new TetrisGui(name, opponentName, local, opponent, keyListener);

	}

}
