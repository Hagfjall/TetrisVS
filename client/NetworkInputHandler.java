package client;

import game.Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkInputHandler extends Thread {
	private Socket s;
	private Game game;
	private String name;

	public NetworkInputHandler(Socket in, Game game) {
		s = in;
		this.game = game;
	}

	public synchronized String getOpponentName() {
		while (name == null) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return name;
	}

	/**
	 * 1: Name of player, the next byte is the length of the name (String)
	 * 
	 * 2: Score (Integer)
	 * 
	 * 3 Left 4 Right 5 Up 6 Down 7 Space 8 Powerup
	 */
	public void run() {
		DataInputStream in;
		try {
			in = new DataInputStream(s.getInputStream());
			int read;
			while ((read = in.read()) != -1) {
				// System.out.print(Character.toChars(read));
				// System.out.println(" = " + read);
				switch (read) {
				case 1:
					name = readString(in);
					notifyAll();
					System.out.println("NetworkInputHandler: recieved " + name);
					break;
				case 2:
					System.out.println("recieved " + in.readInt());
					break;
				case ProtocolConstants.LEFT:
					System.out.println("moving opponent left");
					game.moveLeft();
					break;
				case ProtocolConstants.UP:
					System.out.println("opponent pressing up");
					game.rotateClockwise();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// What the hell should with the name when we has it?
	private String readString(DataInputStream in) throws IOException {
		int length = in.readInt();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(in.readChar());
		}
		return sb.toString();

	}

}
