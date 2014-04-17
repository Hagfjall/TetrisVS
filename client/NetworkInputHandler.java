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
				switch (read) {
				case 1:
					setOpponentName(readString(in));
					break;
				case 2:
					break;
				case ProtocolConstants.LEFT:
					game.moveLeft();
					break;
				case ProtocolConstants.UP:
					game.rotateClockwise();
					break;
				case ProtocolConstants.RIGHT:
					game.moveRight();
					break;
				case ProtocolConstants.DOWN:
					game.rotateCounterClockwise();
					break;
				case ProtocolConstants.SPACE:
					game.moveBottom();
					break;
				} //TODO implementera resten
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void setOpponentName(String s) {
		while (name != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		name = s;
		notifyAll();
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
