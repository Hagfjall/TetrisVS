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
	/**
	 * Initializes everything needed for the Client
	 * 
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Please enter your name");
		if (name == null)
			return;
		String address = JOptionPane
				.showInputDialog("Please enter the server address");
		if (address == null)
			return;
		int port = Integer.parseInt(JOptionPane
				.showInputDialog("Please enter the portnumber"));
		if (port == 0)
			return;
		// String address = "localhost";
		// int port = 3000;
		Socket s;
		try {
			s = new Socket(address, port);
			InitiateConnectionClient init = new InitiateConnectionClient(s,
					name);
			long rndSeed = init.getRndSeed();
			String opponentName = init.getOpponentName();
			long opponentAttackRandomSeed = opponentName.hashCode();
			long localAttackRandomSeed = name.hashCode();
			Game local = new Game(26, 10, rndSeed, localAttackRandomSeed);
			Game opponent = new Game(26, 10, rndSeed, opponentAttackRandomSeed);
			Network network = new Network(s, local, opponent);
			new Thread(network).start();
			KeyListener keyListener = new KeyListener(local, network);
			new TetrisTimer(keyListener, 500);
			new TetrisGui(name, opponentName, local, opponent, keyListener);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Could not connect to " + address + ":" + port + " quitting...");
		}
	}

}
