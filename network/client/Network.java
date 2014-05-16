package network.client;

import game.AttackTimer;
import game.Game;
import game.attacks.Attack;
import game.attacks.AttackFactory;
import game.attacks.NullAttack;

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
	private Attack attack;

	/**
	 * Handles the output and input at the client.
	 * 
	 * @param socket
	 * @param localGame
	 * @param opponentGame
	 */
	public Network(Socket socket, Game localGame, Game opponentGame) {
		this.socket = socket;
		this.localGame = localGame;
		this.opponentGame = opponentGame;
		attack = new NullAttack();
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendAttack(Attack attack) {
		send(ProtocolConstants.X);
		this.attack = attack;
	}

	public void sendKey(byte key) {
		send(key);
	}

	public void sendAttackActivated() {
		send(ProtocolConstants.ATTACK_START);
	}

	public void sendAttackDeactivated() {
		localGame.deactivateAttack();
		send(ProtocolConstants.ATTACK_STOP);
	}

	/**
	 * Reads the input from sent by the server.
	 */
	@Override
	public void run() {
		try {
			int read;
			while ((read = in.read()) != -1) {
				switch (read) {
				case ProtocolConstants.STRING:
					System.err.println("read String should not be done");
					break;
				case ProtocolConstants.INT:
					System.err.println("Read Int should not be done");
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
					System.out.println("Netwokr: recieving powerup");
					attack = opponentGame.useAttack();
					new AttackTimer(this, 30000);
					System.out.println("network: attacktype: "
							+ attack.getType());
					localGame.activateAttack(attack);
					break;
				case ProtocolConstants.ATTACK_START:
					System.out.println("PowerupACK: type: " + attack.getType());
					opponentGame.activateAttack(attack);
					break;
				case ProtocolConstants.ATTACK_STOP:
					opponentGame.deactivateAttack();
					break;
				case ProtocolConstants.MOVEDOWN:
					opponentGame.moveDown();
					break;
				}
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
