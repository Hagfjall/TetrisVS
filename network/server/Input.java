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

	// TODO will not be needed?
	@Override
	public boolean equals(Object o) {
		System.err.println("equals(Object o) in Input should not be used!");
		if (o instanceof Input) {
			return socket.equals(((Input) o).socket);
		}
		return false;
	}

}
