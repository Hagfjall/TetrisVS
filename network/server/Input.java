package network.server;

import java.net.Socket;

public class Input {

	private Socket socket;
	private int[] message;

	public Input(Socket socket, int[] msg) {
		this.socket = socket;
		message = msg;
	}

	public int[] getMessage() {
		return message;
	}

	public Socket getSocket() {
		return socket;
	}

}
