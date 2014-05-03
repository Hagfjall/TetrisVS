package network.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

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
			if ((read = in.read()) == 1) {
				while ((read = in.read()) != -1) {
					int[] msg;
					if (read == 2) {
						msg = new int[5];
						msg[0] = 2;
						byte[] integer = ByteBuffer.allocate(4)
								.putInt(in.readInt()).array();
						for (int i = 0; i < 4; i++) {
							msg[i + 1] = integer[i];
						}
					} else {
						msg = new int[1];
						msg[0] = read;
					}
					m.insert(new Input(socket, msg));
				}
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
