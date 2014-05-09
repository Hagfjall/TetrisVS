package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

public class Server extends Thread {

	private Vector<Socket> allConnections;
	private TetrisMailbox m;

	public static void main(String[] args) {
		new Server().start();
	}

	public Server() {
		allConnections = new Vector<Socket>();
		m = new TetrisMailbox();
	}

	@Override
	public void run() {
		int port = 3000;
		Random rnd = new Random();
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("server up and running on port " + port);
			ServerOutputHandler output = new ServerOutputHandler(
					allConnections, m, rnd.nextLong());
			output.start();

			while (true) {
				Socket s = server.accept();
				allConnections.add(s);
				InputHandler in = new InputHandler(s, m, allConnections);
				in.start();
				output.addPlayername(in.getPlayername());
				if (allConnections.size() == 2) {
					output.setRandomSeed(rnd.nextLong());
					output.initiateGameOnClients();
				} else if (allConnections.size() > 2) {
					allConnections.add(s);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
