package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread {

	private PlayerNames playerNames;
	private Vector<Socket> allConnections;
	private TetrisMailbox m;

	public static void main(String[] args) {
		new Server().start();
	}

	public Server() {
		playerNames = new PlayerNames();
		allConnections = new Vector<Socket>();
		m = new TetrisMailbox();
	}

	@Override
	public void run() {
		int port = 3000;

		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("server up and running on port " + port);
			ServerOutputHandler output = new ServerOutputHandler(
					allConnections, m);
			while (true) {
				Socket s = server.accept();
				allConnections.add(s);
				InputHandler in = new InputHandler(s, m);
				in.start();
				output.addPlayername(in.getPlayername());
				if (allConnections.size() == 2) {
					output.start();
				} else {
					// send all player names since its a spectator
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
