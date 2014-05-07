package client;

import game.Game;
import game.TetrisTimer;
import gui.TetrisGui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import network.client.InitiateConnectionClient;
import network.client.KeyListener;
import network.client.Network;

public class Tetris {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String name = JOptionPane.showInputDialog("Please enter your name");
		if (name == null)
			return;
//		 String address = JOptionPane
//		 .showInputDialog("Please enter the server address");
//		 int port = Integer.parseInt(JOptionPane
//		 .showInputDialog("Please enter the portnumber"));
		 String address = "10.1.2.176"; //"31.208.39.174";
		 int port = 3000;

		Socket s = new Socket(address, port);
		InitiateConnectionClient init = new InitiateConnectionClient(s, name);
		long rndSeed = init.getRndSeed();
		String opponentName = init.getOpponentName();
		Game local = new Game(22, 10, rndSeed);
		Game opponent = new Game(22, 10, rndSeed);
		Network network = new Network(s,local,opponent);
		new Thread(network).start();
		KeyListener keyListener = new KeyListener(local, network);
		new TetrisTimer(keyListener, 750);
		new TetrisGui(name, opponentName, local, opponent, keyListener);

	}

}
