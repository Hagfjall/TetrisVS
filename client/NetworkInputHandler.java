package client;

import game.Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import network.CommonNetworkMethods;
import network.ProtocolConstants;

public class NetworkInputHandler extends Thread {
	private Socket socket;
	private DataInputStream in;
	private Game game;
	private String name;
	private long randomSeed;
	public NetworkInputHandler(Socket socketIn, Game game) {
		socket = socketIn;
		this.game = game;
		//TODO read name
		try {
			name = CommonNetworkMethods.readString(in);
			randomSeed = in.readLong();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
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
			in = new DataInputStream(socket.getInputStream());
			int read;
			while ((read = in.read()) != -1) {
				switch (read) {
				case ProtocolConstants.STRING:
					System.err.println("read String ska inte göras");
					break;
				case ProtocolConstants.INT:
					System.err.println("Read Int ska inte göras");
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
				case ProtocolConstants.POWERUP:
					game.usePowerup();
					break;
				} // TODO implementera resten
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

	

}
