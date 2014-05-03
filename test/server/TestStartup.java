package test.server;

import game.Game;
import gui.TetrisGui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import client.InitiateConnectionClient;
import client.KeyListener;
import client.NetworkInputHandler;
import client.NetworkOutputHandler;

public class TestStartup {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String name ="he2";
		String address = "localhost";
		int port = 3001;
		Socket s = new Socket(address, port);
		InitiateConnectionClient init = new InitiateConnectionClient(s, name);
		long rndSeed = init.getRndSeed();
		String opponentName = init.getOpponentName();
		Game local = new Game(22, 10, rndSeed);
		Game opponent = new Game(22, 10, rndSeed, false);
		NetworkOutputHandler nout = new NetworkOutputHandler(s);
		NetworkInputHandler nin = new NetworkInputHandler(s, opponent);
		nin.start();
		System.out.println("main worked!"); 
		System.out.println("recieved: " + rndSeed + " name: " + opponentName);
	}
	

}
