package client;

import game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import network.ProtocolConstants;

public class KeyListener implements java.awt.event.KeyListener {

	private Game localGame;
	private NetworkOutputHandler network;

	public KeyListener(Game localGame, NetworkOutputHandler network) {
		this.localGame = localGame;
		this.network = network;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

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
			localGame.usePowerup();
			network.sendKey(ProtocolConstants.POWERUP);
			break;	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	

}
