package server;

import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String[] args) {

		new Server().start();
	}

	private void start() {
		int port = 3000;

		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket s = server.accept();
				Connection c = new Connection(s);
				c.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
