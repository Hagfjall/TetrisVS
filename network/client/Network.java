package network.client;

import game.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.ProtocolConstants;

public class Network implements Runnable {

	private final Game localGame, opponentGame;
	private final Socket socket;
	private DataOutputStream out;
	private DataInputStream in;

	public Network(Socket socket, Game localGame, Game opponentGame) {
		this.socket = socket;
		this.localGame = localGame;
		this.opponentGame = opponentGame;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void sendKey(byte key) {
		send(key);
	}

	public void sendPowerupAck(byte pwrUp) {
		send(ProtocolConstants.POWERUP_ACK);
		send(pwrUp);
	}

	@Override
	public void run() {
		try {
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
					opponentGame.moveLeft();
					break;
				case ProtocolConstants.UP:
					opponentGame.rotateClockwise();
					break;
				case ProtocolConstants.RIGHT:
					opponentGame.moveRight();
					break;
				case ProtocolConstants.DOWN:
					opponentGame.rotateCounterClockwise();
					break;
				case ProtocolConstants.SPACE:
					opponentGame.moveBottom();
					break;
				case ProtocolConstants.X:
					System.out.print("recieving powerup");
					byte pwrUp = in.readByte();
					System.out.println(" nbr " + pwrUp);
					localGame.activatePowerup(pwrUp);
					sendPowerupAck(pwrUp);
					break;
				case ProtocolConstants.POWERUP_ACK:
					System.out.print("recieving powerupACK");
					pwrUp = in.readByte();
					System.out.println(" nbr " + pwrUp);
					opponentGame.activatePowerup(pwrUp);
					break;
				case ProtocolConstants.MOVEDOWN:
					opponentGame.moveDown();
					break;
				} // TODO implementera resten
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send(byte type) {
		try {
			out.write(type);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
