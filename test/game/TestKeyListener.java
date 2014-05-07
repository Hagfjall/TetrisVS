package test.game;

import game.Game;

import java.awt.event.KeyEvent;

import network.ProtocolConstants;
import network.client.KeyListener;

public class TestKeyListener extends KeyListener implements java.awt.event.KeyListener {

	private Game localGame;

	public TestKeyListener(Game localGame) {
		super(localGame,null);
		this.localGame = localGame;
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
	
	public void moveDown(){
		localGame.moveDown();
	}

}
