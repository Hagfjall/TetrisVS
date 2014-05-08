package network.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import network.CommonNetworkMethods;

public class InputHandler extends Thread {
	private Socket socket;
	private InputStream is;
	private TetrisMailbox m;
	private String playerName;

	public InputHandler(Socket socket, TetrisMailbox m) throws IOException {
		this.m = m;
		this.socket = socket;
		is = socket.getInputStream();
		playerName = CommonNetworkMethods.readString(new DataInputStream(socket
				.getInputStream()));
	}

	public String getPlayername() {
		return playerName;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(is);
			int read;
			while ((read = in.read()) != -1) {
				int[] msg = new int[1];
				msg[0] = read;
				m.insert(new Input(socket,msg));
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
