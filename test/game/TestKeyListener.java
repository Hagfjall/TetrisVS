package test.game;

import game.Game;

import java.awt.event.KeyEvent;

import client.NetworkOutputHandler;
import client.ProtocolConstants;

public class TestKeyListener implements java.awt.event.KeyListener {

	private Game localGame;
	private NetworkOutputHandler network;

	public TestKeyListener(Game localGame, NetworkOutputHandler network) {
		this.localGame = localGame;
		this.network = network;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		switch (input) {
		case KeyEvent.VK_UP:
			localGame.rotateClockwise();
			break;
		case KeyEvent.VK_DOWN:
			localGame.rotateCounterClockwise();
			break;
		case KeyEvent.VK_LEFT:
			localGame.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			localGame.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			localGame.moveBottom();
			break;
		case KeyEvent.VK_X:
			localGame.usePowerup();
			break;	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
