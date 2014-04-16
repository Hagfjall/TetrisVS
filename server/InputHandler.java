package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class InputHandler extends Thread {

	private Socket socket;
	private TetrisMailbox m;
	private PlayerNames playerNames;

	public InputHandler(Socket socket, TetrisMailbox m, PlayerNames playerNames) {
		this.m = m;
		this.socket = socket;
		this.playerNames = playerNames;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			int read;
			// check if the first thing the client sends is the name, close
			// otherwise this is to be sure that we are following the protocol
			if ((read = in.read()) == 1) {
				int length = in.readInt();
				StringBuilder playerName = new StringBuilder(length);
				for (int i = 0; i < length; i++) {
					playerName.append(in.readChar());
				}
				System.out.println("InputHanlder: setting playername to " + playerName);
				if (!playerNames.setName(playerName.toString(), socket)) {
					socket.close();
					return;
				}
				while ((read = in.read()) != -1) {
					System.out.println("InputHanlder: waiting for inputs...");
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
			System.out.println("closing inputhandler");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
