package network.client;

import game.Game;
import game.powerups.Powerup;

import java.awt.event.KeyEvent;

import network.ProtocolConstants;

public class KeyListener implements java.awt.event.KeyListener {

	private Game localGame;
	private Network network;

	public KeyListener(Game localGame, Network network) {
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
			Powerup pwrUp;
			if ((pwrUp = localGame.getPowerup()) != null) {
				System.out.println("sending popweup");
				network.sendKey(ProtocolConstants.X);
				network.sendKey(pwrUp.getType());
			}
			break;
		}

	}
	public void moveDown(){
		localGame.moveDown();
		network.sendKey(ProtocolConstants.MOVEDOWN);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
