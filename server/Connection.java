package server;

import java.net.*;

public class Connection extends Thread {
	private Socket s;

	public Connection(Socket socket) {
		s = socket;
	}

	public void run() {

	}
}
