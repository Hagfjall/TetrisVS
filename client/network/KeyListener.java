package client.network;

import java.awt.event.KeyEvent;

import client.game.Game;
import client.game.attacks.Attack;
import network.ProtocolConstants;

public class KeyListener implements java.awt.event.KeyListener {
	private Game localGame;
	private Network network;

	/**
	 * Listens for keys pressed on the keyboard locally and send pass them to
	 * the server and the local game.
	 * 
	 * @param localGame
	 * @param network
	 */

	public KeyListener(Game localGame, Network network) {
		this.localGame = localGame;
		this.network = network;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Calls the appropriate method in local game and sends the key pressed to
	 * the server.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		switch (input) {
		case KeyEvent.VK_UP:
			localGame.rotateClockwise();
			network.sendKey(ProtocolConstants.UP);
			break;
		case KeyEvent.VK_DOWN:
			localGame.rotateCounterClockwise();
			network.sendKey(ProtocolConstants.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			localGame.moveLeft();
			network.sendKey(ProtocolConstants.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			localGame.moveRight();
			network.sendKey(ProtocolConstants.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			localGame.moveBottom();
			network.sendKey(ProtocolConstants.SPACE);
			break;
		case KeyEvent.VK_X:
			Attack attack = localGame.useAttack();
			// only sending if there are anything to send.
			if (attack.getType() != Attack.NULL_ATTACK) {
				network.sendAttack(attack);
			}
			break;
		}

	}

	/**
	 * Called by the timer.
	 */
	public void moveDown() {
		localGame.moveDown();
		network.sendKey(ProtocolConstants.MOVEDOWN);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


}
