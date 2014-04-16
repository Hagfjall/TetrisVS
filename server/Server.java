package server;

import java.io.DataOutputStream;
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
			int inputCounter = 0;
			new OutputHandler(allConnections, m).start();;
			while (true) {
				Socket s = server.accept();
				if (inputCounter++ < 2) {
					new InputHandler(s, m, playerNames).start();
					System.out.println("opening new InputHanlder, counter = " + inputCounter);
					sendOpponentPlayerName(s);
				}else {
					// send all player names since its a spectator
				}
				allConnections.add(s);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendOpponentPlayerName(Socket s) throws IOException {
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		String name = playerNames.getPlayername(s);
		if (name != null) {
			out.write(1);
			out.writeInt(name.length());
			out.writeChars(name);
		}
	}
}
