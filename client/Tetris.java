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
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String name = JOptionPane.showInputDialog("Please enter your name");
//		String address = JOptionPane
//				.showInputDialog("Please enter the server address");
//		int port = Integer.parseInt(JOptionPane
//				.showInputDialog("Please enter the portnumber"));
		String address = "192.168.43.180";
		int port = 3000;
		Socket s = new Socket(address, port);
		InitiateConnectionClient init = new InitiateConnectionClient(s, name);
		long rndSeed = init.getRndSeed();
		String opponentName = init.getOpponentName();
		long opponentPowerupRandomSeed = name.hashCode();
		long localPowerupRandomSeed = opponentName.hashCode();
		Game local = new Game(22, 10, rndSeed, localPowerupRandomSeed);
		Game opponent = new Game(22, 10, rndSeed, opponentPowerupRandomSeed);
		Network network = new Network(s, local, opponent);
		System.out.println("Local name: "+ name);
		System.out.println("Local seed: " + localPowerupRandomSeed);
		System.out.println("Opponent name: " + opponentName);
		System.out.println("Opponent seed: " + opponentPowerupRandomSeed);
		new Thread(network).start();
		KeyListener keyListener = new KeyListener(local, network);
		new TetrisTimer(keyListener, 750);
		new TetrisGui(name, opponentName, local, opponent, keyListener);

	}

}
