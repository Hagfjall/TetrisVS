package network.client;

import game.Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import network.ProtocolConstants;

/**
 * Used for steering the opponentGame 
 * @author Fredrik Hagfjäll
 *
 */
public class NetworkInputHandler extends Thread {
	private Socket socket;
	private Game game;
	public NetworkInputHandler(Socket socketIn, Game game) {
		socket = socketIn;
		this.game = game;
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
					System.out.print("recieving powerup");
					byte pwrUp = in.readByte();
					System.out.println(" nbr " + pwrUp);
					game.usePowerup(pwrUp);
					break;
				case ProtocolConstants.MOVEDOWN:
					game.moveDown();
					break;
				} // TODO implementera resten
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
